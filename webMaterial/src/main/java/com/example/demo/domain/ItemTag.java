package com.example.demo.domain;



    public enum ItemTag {
        

        /** 1:かわいい */
        cute(1,"かわいい"),

        /** 2: かっこいい */
        cool(2,"かっこいい"),

        /** 3: おしゃれ */
        stylish(3,"おしゃれ"),

        /** 4: シンプル */
        simple(4,"シンプル"),

        /** 5: ネタ */
        funny(5,"ネタ");

        private int value;
        private String label; 

        private ItemTag(int value, String label) {
            this.value = value;
            this.label = label;
        }

        private ItemTag(int value) {
            this.value = value;
        }
        private ItemTag(String label) {
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
            return this.label;
         }

        /**
         * 指定されたコード値に対応する列挙型を取得します。
         * @param value コード値
         * @return 帳票マスタコード列挙型
         */
        public static ItemTag of(int value) {
            return java.util.Arrays.stream(ItemTag.class.getEnumConstants())
                    .filter(e -> e.getValue() == value)
                    .findFirst().get();
        }
    }
    
    