package com.ghost.perfilProfissional.models.enums;

public enum TipoCargo {
    DESENVOLVEDOR(0, "DESENVOLVEDOR"),
    DESIGNER(1, "DESIGNER"),
    SUPORTE(2, "SUPORTE"),
    TESTER(3, "TESTER");

    private int cod;
    private String descricao;

    private TipoCargo(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }

    public static TipoCargo toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for (TipoCargo x : TipoCargo.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: "+ cod);
    }
    public static Integer toCod(String s){
        if(s == null){
            return null;
        }

        for (TipoCargo x : TipoCargo.values()){
            if (s.equals(x.name())){
                return x.cod;
            }
        }
        throw new IllegalArgumentException("String Inválida: "+ s);
    }
}
