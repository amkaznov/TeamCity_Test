package com.teamcity.api.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teamcity.api.annotations.Parameterizable;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties extends BaseModel {

    @Parameterizable
    private List<Property> property;

}
