package eblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eblog.domain.Catagory;
import eblog.dto.CatagoryDto;
import eblog.exception.ResourceNotFoundException;
import eblog.repository.CatagoryRepository;
import eblog.service.CatagoryService;

@Service
public class CatagoryServiceImpl implements CatagoryService {

	@Autowired
	private CatagoryRepository repository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CatagoryDto createCatagory(CatagoryDto dto) {
		return this.convertToDto(this.repository.save(this.convertToCatagory(dto)));
	}

	@Override
	public CatagoryDto getCatagory(int id) {
		Catagory catagory = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", " Id ", id));
		return this.convertToDto(catagory);
	}

	@Override
	public List<CatagoryDto> getAllCatagory() {
		List<Catagory> catagoryList = this.repository.findAll();
		return catagoryList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public CatagoryDto updateCatagory(CatagoryDto dto, int id) {
		Catagory catagory = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", " Id ", id));

		return this.convertToDto(this.repository.save(catagory));
	}

	@Override
	public void deleteCatagory(int id) {
		Catagory catagory = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", " Id ", id));

		this.repository.delete(catagory);
	}

	public CatagoryDto convertToDto(Catagory catagory) {
		return this.modelMapper.map(catagory, CatagoryDto.class);

	}

	public Catagory convertToCatagory(CatagoryDto dto) {
		return this.modelMapper.map(dto, Catagory.class);

	}

}
