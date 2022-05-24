package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor
@Data
class Park extends Object{
    @NotEmpty(message = "id is required")
    @Size(min = 2)
    private String rideid;
    @NotEmpty(message = "name is required")
    @Size(min = 4)
    private String ridename;
    @NotEmpty(message ="ridetyr is required" )
    @Pattern(regexp = "Rollercoaster|thriller|water",message = " The ridetyr must be Rollercoaster|thriller|wate")
    private String ridetyr;
    @NotNull(message = " ticket is required")
    private Integer ticket;
    @NotNull(message = " price is required")
    private Integer price;



}
