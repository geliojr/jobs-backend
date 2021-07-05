package com.ghost.perfilProfissional.utils;

import com.ghost.perfilProfissional.models.enums.TipoCargo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    //Utiliza expressão regular para verifcar se a String é uma data.
    public static boolean isValidStringDate(String element) {

        String regex = "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$";

        Pattern p = Pattern.compile(regex);

        if (element == null) {
            return false;
        }

        Matcher m = p.matcher(element);

        return m.matches();
    }

    //Utiliza expressão regular para verifcar se a String é um numero inteiro.
    public static boolean isIntegerNumber(String element) {

        String regex = "^[0-9 ]*$";

        Pattern p = Pattern.compile(regex);

        if (element == null) {
            return false;
        }

        Matcher m = p.matcher(element);

        return m.matches();
    }

    //Utiliza expressão literal para verificar se a String é do tipo alfanumérico.
    public static boolean isOnlyString(String element) {

        String regex = "^[a-zA-Z0-9 ]*$";

        Pattern p = Pattern.compile(regex);

        if (element == null) {
            return false;
        }

        Matcher m = p.matcher(element);

        return m.matches();
    }

    //Verifica se a String é equivalente a um TipoCargo retornando boolean.
    public static boolean isEnumTipoCargo(String element) {

        if (element == null) {
            return false;
        }

        for (TipoCargo x : TipoCargo.values()){

            if (element.equals(x.name())){
                return true;
            }
        }

        return false;
    }

}
