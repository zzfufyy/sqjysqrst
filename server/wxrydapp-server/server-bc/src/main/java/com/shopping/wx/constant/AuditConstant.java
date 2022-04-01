package com.shopping.wx.constant;

/**
 * @author ljy
 * @date 2022-03-11 12:40
 */
public interface AuditConstant {

    /**
     * 删除状态字段的名称
     */
    String STATUS_FIELD_NAME = "status";

    /**
     * 插入时间字段的名称
     */
    String CREATE_TIME_FIELD_NAME = "createTime";

    /**
     * id 字段的名称
     */
    String ID_FIELD_NAME = "id";

    /**
     * 枚举一条记录的状态（逻辑删除状态），0 启用，-1 删除
     */
    enum RecordStatus {
        /**
         * 启用状态
         */
        ACTIVE(0),
        /**
         * 删除状态
         */
        DELETED(-1);

        RecordStatus(int value) {
            this.value = value;
        }

        private final int value;

        public int value() {
            return value;
        }
    }

    /**
     * 认证状态
     */
    enum IdentifyStatus {
        SUCCESS(1),
        NORMAL(0),
        FAIL(-1);

        IdentifyStatus(int value) {
            this.value = value;
        }

        private final int value;

        public int getValue() {
            return value;
        }
    }

    /**
     * 薪资比较转台
     */
    enum SalaryCompareState {
        NEOGIATION(0),
        UNDERSALARY(1),
        UPERSALARY(2),
        BETWEENSALARY(3);

        private final int value;

        SalaryCompareState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
