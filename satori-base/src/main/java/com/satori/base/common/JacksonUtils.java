package com.satori.base.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

import static com.satori.base.common.Formaters.DATE_FORMATTER;
import static com.satori.base.common.Formaters.DATE_TIME_FORMATTER;

/**
 * @author ChenHao
 * @date 2023/8/7 10:32
 */
@UtilityClass
public class JacksonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.registerModule(satoriModule());
        SimpleDateFormat dateFormat = new SimpleDateFormat(Formaters.YYYY_MM_DD_HH_MM_SS);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.setDateFormat(dateFormat);
        // 反序列化未知属性不报错
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Module satoriModule(){
        SimpleModule module = new SimpleModule("satoriModule");
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER));
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));

        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        return module;
    }

    @SneakyThrows
    public static String toJsonString(Object obj){
        if (obj == null) {
            return null;
        }
        return OBJECT_MAPPER.writeValueAsString(obj);
    }
    @SneakyThrows
    public static byte[] toBytes(Object obj){
        if (obj == null) {
            return null;
        }
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }
    @SneakyThrows
    public static <T> T parseObj(String jsonText, Class<T> clazz){
        if (jsonText == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(jsonText, clazz);
    }
    @SneakyThrows
    public static <T> T parseObj(String jsonText, JavaType javaType){
        if (jsonText == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(jsonText, javaType);
    }
    @SneakyThrows
    public static <T> T parseObj(String jsonText, TypeReference<T> typeReference){
        if (jsonText == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(jsonText, typeReference);
    }

    @SneakyThrows
    public static JsonNode parseObj(String jsonText){
        return OBJECT_MAPPER.readTree(jsonText);
    }

    @SneakyThrows
    public static <T> T parseObj(byte[] bytes, Class<T> clazz){
        if (bytes == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(bytes, clazz);
    }
    @SneakyThrows
    public static <T> T parseObj(byte[] bytes, JavaType javaType){
        if (bytes == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(bytes, javaType);
    }
    @SneakyThrows
    public static <T> T parseObj(byte[] bytes, TypeReference<T> typeReference){
        if (bytes == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(bytes, typeReference);
    }



    public static <T> T convertValue(Object fromValue, Class<T> toValueType){
        return OBJECT_MAPPER.convertValue(fromValue, toValueType);
    }

    public <T> T convertValue(Object fromValue, JavaType toValueType){
        return OBJECT_MAPPER.convertValue(fromValue, toValueType);
    }

    public <T> T convertValue(Object fromValue, TypeReference<T> toValueType){
        return OBJECT_MAPPER.convertValue(fromValue, toValueType);
    }

    public static void main(String[] args) {
        String str = "{\"acct_date\":\"20230824\",\"acct_id\":\"F01497768\",\"acct_split_bunch\":{\"acct_infos\":[{\"acct_date\":\"20230824\",\"acct_id\":\"F01497768\",\"div_amt\":\"0.03\",\"huifu_id\":\"6666000137215868\"}],\"fee_acct_date\":\"20230824\",\"fee_acct_id\":\"F01497768\",\"fee_amt\":\"0.00\",\"fee_huifu_id\":\"6666000137215868\"},\"acct_stat\":\"S\",\"atu_sub_mer_id\":\"588046324\",\"avoid_sms_flag\":\"\",\"bagent_id\":\"6666000135374776\",\"bank_code\":\"SUCCESS\",\"bank_desc\":\"支付成功\",\"bank_message\":\"支付成功\",\"bank_order_no\":\"4200067685202308246513165940\",\"bank_seq_id\":\"000607\",\"bank_type\":\"ZJRCUB\",\"base_acct_id\":\"F01497768\",\"batch_id\":\"230824\",\"channel_type\":\"N\",\"charge_flags\":\"758_0\",\"combinedpay_data\":[],\"combinedpay_fee_amt\":\"0.00\",\"debit_flag\":\"1\",\"debit_type\":\"D\",\"delay_acct_flag\":\"N\",\"div_flag\":\"0\",\"end_time\":\"20230824105226\",\"fee_amount\":\"0.00\",\"fee_amt\":\"0.00\",\"fee_flag\":2,\"fee_rec_type\":\"1\",\"fee_type\":\"INNER\",\"gate_id\":\"VX\",\"hf_seq_id\":\"002900TOP1A230824105212P846ac139c4e00000\",\"huifu_id\":\"6666000137215868\",\"is_delay_acct\":\"0\",\"is_div\":\"0\",\"mer_name\":\"杭州游橙旅游有限公司\",\"mer_ord_id\":\"102308240000000607\",\"mypaytsf_discount\":\"0.00\",\"need_big_object\":false,\"notify_type\":2,\"org_auth_no\":\"\",\"org_huifu_seq_id\":\"\",\"org_trans_date\":\"\",\"out_ord_id\":\"4200067685202308246513165940\",\"out_trans_id\":\"4200067685202308246513165940\",\"party_order_id\":\"03222308243913307515904\",\"pay_amt\":\"0.03\",\"pay_scene\":\"02\",\"posp_seq_id\":\"03222308243913307515904\",\"product_id\":\"KAZX\",\"ref_no\":\"105212000607\",\"req_date\":\"20230824\",\"req_seq_id\":\"102308240000000607\",\"resp_code\":\"00000000\",\"resp_desc\":\"交易成功\",\"risk_check_data\":{\"ip_addr\":\"192.168.10.22\"},\"risk_check_info\":{\"client_ip\":\"192.168.10.22\"},\"settlement_amt\":\"0.03\",\"sub_resp_code\":\"00000000\",\"sub_resp_desc\":\"交易成功\",\"subsidy_stat\":\"I\",\"sys_id\":\"6666000135374776\",\"trade_type\":\"T_MICROPAY\",\"trans_amt\":\"0.03\",\"trans_date\":\"20230824\",\"trans_fee_allowance_info\":{\"actual_fee_amt\":\"0.00\",\"allowance_fee_amt\":\"0.00\",\"allowance_type\":\"0\",\"receivable_fee_amt\":\"0.00\"},\"trans_stat\":\"S\",\"trans_time\":\"105212\",\"trans_type\":\"T_MICROPAY\",\"ts_encash_detail\":[],\"wx_response\":{\"bank_type\":\"ZJRCUB\",\"coupon_fee\":\"0.00\",\"openid\":\"o8jhot1gF_gjwdokViW82eWunboI\",\"sub_appid\":\"wxf0e0e8533a306c68\",\"sub_openid\":\"oDjcC5pFjjZOrBKXy1MdnLN4sL5o\"}}";
        JsonNode jsonNode = parseObj(str);
        System.out.println(jsonNode);
        System.out.println(jsonNode.get("notify_type").asInt());
    }

}
