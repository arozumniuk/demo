package io.result.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreatedUser {
    String name;
    String job;
    int id;
    String createdAt;
}
