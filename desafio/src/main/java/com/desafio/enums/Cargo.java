package com.desafio.enums;

public enum Cargo {

    DESENVOLVEDOR(1),
    DESIGNER(2),
    SUPORTE(3),
    TESTER(4);

    private Integer descricao;

    Cargo(Integer descricao) {
        this.descricao = descricao;
    }

    public Integer getDescricao() {
        return descricao;
    }

    public static int findByNumber(int number) {
        for (Cargo cargo : values()) {
            if (cargo.getDescricao() == number) {
                return number;
            }
        }
        return 0;
    }
}
