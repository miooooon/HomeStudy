package com.example.demo.domain;

public enum ItemType {
    //TODO 仮決め
    
    /** 1:イラスト */
    イラスト(1),

    /** 2: 写真 */
    写真(2),

    /** 3: アイコン */
    アイコン(3);

    private int value;

    private ItemType(int value) {
        this.value = value;
    }

    /**
     * 列挙型が保持するコード値を取得します。
     * @return コード値
     */
    public int getValue() {
       return this.value;
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
    


}
