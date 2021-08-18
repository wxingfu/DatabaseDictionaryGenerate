package com.wxf.service;

import com.wxf.dto.ConnDto;
import com.wxf.entity.Table;

import java.util.List;

public interface DatabaseService {

    List<Table> getTables(ConnDto connDto);
}
