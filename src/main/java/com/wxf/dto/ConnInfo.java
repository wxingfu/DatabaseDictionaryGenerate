
package com.wxf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnInfo {

    private String username;
    private String password;
    private String url;
    private String driverClass;
    private String dbname;
}
