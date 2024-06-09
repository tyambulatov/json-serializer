package ru.timur.json;

import ru.timur.json.archive.User;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class UsersRequestProcessor implements ReqProcessor {

    private void postRequest(HttpRequest httpRequest) throws IllegalAccessException {
        MyDeserializer des = null;
        User userToCreate = des.parse(httpRequest.getBody(), User.class);

        User myUser = null;
//        {
//            "login": "abc",
//            "email": "abd@mail.ru",
//            "roles": [
//              {"type": "ADMIN", "createdAt": "abc"},
//              {"type": "USER", "createdAt": "2020-01-01"}
//            ]
//        }
        MySerializer ser = null;
//        ser.preferredType = FIELD/METHOD
        ser.writeAsString(myUser);





        /// cache - последняя очередь, после того как все заработает
        // без кеша - 10 мс
        // с кешем - 1 мс

        StringBuilder sb = new StringBuilder();
        Class<? extends User> userClass = myUser.getClass();
        String rootTag = userClass.getSimpleName();

        sb.append("<").append(rootTag).append(">");


        Field[] fields = userClass.getFields();
//        userClass.getMethods()
                // getName -> "name": "vasya"
                // isActive -> "active": true
        Map<String, FieldGetter> fieldNameToGetter = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldNameToGetter.put(field.getName(), field::get);
        }

        // ()()[]()
        // ((([])[][]))
        // (([)])
        // { "dssdf": "fds", "nestedObjects": [{...}, {...}] }
        // String -> "abc"
        // Number -> 123
        // Boolean -> true/false

        for (Map.Entry<String, FieldGetter> entry : fieldNameToGetter.entrySet()) {
            String name = entry.getKey();
            FieldGetter getter = entry.getValue();
            Object value = getter.getValue(myUser);
            sb.append(name);
            // value - примитив - пишем
            sb.append(value);
            // value - pojo -
            sb.append(ser.writeAsString(value));
        }


        // мы можем 1 раз посчитать
        Map<Class<?>, <rootTag,fieldNameToGetter> > classToHowToSerialize = new HashMap<>();

    }

    interface FieldGetter {
        Object getValue(Object owner) throws IllegalAccessException;
    }
}
