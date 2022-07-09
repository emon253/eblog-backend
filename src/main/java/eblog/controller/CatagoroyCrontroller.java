package eblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eblog.dto.CatagoryDto;
import eblog.service.CatagoryService;

@RestController
@RequestMapping("/api/catagory")
public class CatagoroyCrontroller {

	@Autowired
	private CatagoryService service;

	// create
	@PostMapping("/")
	public ResponseEntity<CatagoryDto> createCatagory(@Valid @RequestBody CatagoryDto catagoryDto) {

		return new ResponseEntity<CatagoryDto>(this.service.createCatagory(catagoryDto), HttpStatus.CREATED);

	}

	// retrieve
	@GetMapping("/{id}")
	public ResponseEntity<CatagoryDto> getCatagory(@PathVariable("id") int id){
		return new ResponseEntity<CatagoryDto>(this.service.getCatagory(id), HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CatagoryDto>> getAllCatagory(){
		return ResponseEntity.ok(this.service.getAllCatagory());
	}
	// update
	@PutMapping("/{id}")
	public ResponseEntity<CatagoryDto>  updateCatagory(@Valid @RequestBody CatagoryDto catagoryDto, @PathVariable("id") int id){
		return new ResponseEntity<CatagoryDto>(this.service.updateCatagory(catagoryDto, id), HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCatagory(@PathVariable("id") int id){
		this.service.deleteCatagory(id);
		return ResponseEntity.ok("Catagory Deleted");
	}
}
