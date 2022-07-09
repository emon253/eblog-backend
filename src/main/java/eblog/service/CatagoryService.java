package eblog.service;

import java.util.List;

import eblog.dto.CatagoryDto;

public interface CatagoryService {

	// create
	CatagoryDto createCatagory(CatagoryDto dto);

	// retrieve;
	CatagoryDto getCatagory(int id);

	List<CatagoryDto> getAllCatagory();

	// update
	CatagoryDto updateCatagory(CatagoryDto dto, int id);

	// delete
	void deleteCatagory(int id);
}
