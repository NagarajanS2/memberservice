package com.aetna.member.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aetna.member.domain.AetnaMember;
import com.aetna.member.domain.DependentResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dependentservice")
@Tag(name = "Aetna Member Dependents", description = "Aetna Member Dependent Service API")
public interface DependentResource {

	@Operation(summary = "Add new Dependent", description = "", tags = { "Aetna Member Dependents" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = DependentResponse.class)))})
	@PostMapping(path =  "/adddependent", produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public ResponseEntity<DependentResponse> addDependent(@RequestBody AetnaMember aetnaMember);
	
	
	@Operation(summary = "Modify existing Dependent", description = "", tags = { "Aetna Member Dependents" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = DependentResponse.class)))})
	@PostMapping(path =  "/modifydependent", produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public ResponseEntity<DependentResponse> modifyDependent(@RequestBody AetnaMember aetnaMember);

	
	@Operation(summary = "List Dependent Details", description = "", tags = { "Aetna Member Dependents" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = DependentResponse.class)))})
	@GetMapping(path = "/listdependent/{memberId}")
	@ResponseBody
	public ResponseEntity<DependentResponse> listDependents(@PathVariable("memberId") String memberId);
	
	@Operation(summary = "Remove Member Dependent", description = "", tags = { "Aetna Member Dependents" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = DependentResponse.class)))})
	@DeleteMapping(path = "/removdependent/{depedentId}")
	@ResponseBody
	public ResponseEntity<DependentResponse> removDependent(@PathVariable("depedentId") String dependentId);
}
