package jesse.service;

import jesse.domain.Province;

import java.util.List;

public interface ProvinceService {

    public List<Province> findALL();

    public String findAllJson();

}
