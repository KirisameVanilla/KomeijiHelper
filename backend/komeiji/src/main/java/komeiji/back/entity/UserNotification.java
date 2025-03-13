package komeiji.back.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public record UserNotification(int type, String content, int from, int to) {

    // 转换单个对象为 JSON 字符串
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    // 转换 List<UserNotification> 为 JSON 字符串
    public static String listToJson(List<UserNotification> list) {
        try {
            return new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    // 从 JSON 解析单个对象
    public static UserNotification fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, UserNotification.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    // 从 JSON 解析 List<UserNotification>
    public static List<UserNotification> listFromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, new TypeReference<List<UserNotification>>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
