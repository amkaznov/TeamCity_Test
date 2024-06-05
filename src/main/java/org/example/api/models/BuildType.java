package org.example.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildType {
    private String id;
    private String name ;
    private NewProjectDescription project;
}
