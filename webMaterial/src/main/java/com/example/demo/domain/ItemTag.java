package com.example.demo.domain;



    public enum ItemTag {
        

        /** 1:かわいい */
        かわいい(1),

        /** 2: かっこいい */
        かっこいい(2),

        /** 3: おしゃれ */
        おしゃれ(3),

        /** 4: シンプル */
        シンプル(4),

        /** 5: ネタ */
        ネタ(5),

        /** 6: 海外出張申請 */
        海外出張(6),

        /** 7: 特別休暇申請 */
        特別休暇(7),

        /** 8: 育児介護休業申請 */
        育児介護休業(8),

        /** 9: 欠勤申請 */
        欠勤(9);

        private int value;

        private ItemTag(int value) {
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
        public static ItemTag of(int value) {
            return java.util.Arrays.stream(ItemTag.class.getEnumConstants())
                    .filter(e -> e.getValue() == value)
                    .findFirst().get();
        }
    }
    