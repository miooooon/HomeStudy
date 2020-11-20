package com.example.demo.domain;

public enum ItemType {
    //TODO 仮決め
    
    /** 1:イラスト */
    picture(1,"イラスト"),

    /** 2: 写真 */
    photo(2,"写真"),

    /** 3: アイコン */
    icon(3,"アイコン");

    private int value;
    private String label; 

    private ItemType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 列挙型が保持するコード値を取得します。
     * @return コード値
     */
    public int getValue() {
       return this.value;
    }
    public String getLabel() {
        return label;
    }

    /**
     * 指定されたコード値に対応する列挙型を取得します。
     * @param value コード値
     * @return 帳票マスタコード列挙型
     */
    public static ItemType of(int value) {
        return java.util.Arrays.stream(ItemType.class.getEnumConstants())
                .filter(e -> e.getValue() == value)
                .findFirst().get();
    }
    
    /**
     * 指定されたコード値に対応する列挙型を取得します。
     * @param value コード値
     * @return 帳票マスタコード列挙型
     */
    public static ItemType of(String label) {
        return java.util.Arrays.stream(ItemType.class.getEnumConstants())
                .filter(e -> e.getLabel() == label)
                .findFirst().get();
    }
    

}
