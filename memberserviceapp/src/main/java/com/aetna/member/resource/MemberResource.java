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
import com.aetna.member.domain.MemberResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/memservice")
@Tag(name = "Aetna Member", description = "Aetna Member Service API")
public interface MemberResource {

	//http://localhost:8085/memservice/healthCheck
	//http://localhost:8085/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Aetna%20Member/addAetnaMember
	
	@Operation(summary = "Add new Aetna Member", description = "", tags = { "Aetna Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = MemberResponse.class)))})
	@PostMapping(path =  "/addmember", produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public ResponseEntity<MemberResponse> addAetnaMember(@RequestBody AetnaMember aetnaMember);
	
	
	
	@Operation(summary = "View Aetna Member", description = "", tags = { "Aetna Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = MemberResponse.class)))})
	@GetMapping(path = "/viewmember/{memberId}")
	@ResponseBody
	public ResponseEntity<MemberResponse> viewAetnaMember(@PathVariable("memberId") String memberId);
	
	
	@Operation(summary = "Modify existing Member Details", description = "", tags = { "Aetna Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = MemberResponse.class)))})
	@PostMapping(path =  "/modifydependent", produces = "application/json", consumes = "application/json" )
	@ResponseBody
	public ResponseEntity<MemberResponse> modifyMember(@RequestBody AetnaMember aetnaMember);
	
	
	@Operation(summary = "Remove Aetna Member", description = "", tags = { "Aetna Member" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK", content = @Content(schema = @Schema(implementation = MemberResponse.class)))})
	@DeleteMapping(path = "/removemember/{memberId}")
	@ResponseBody
	public ResponseEntity<MemberResponse> removeAetnaMember(@PathVariable("memberId") String memberId);
	
	/*@GetMapping(path = "/healthCheck")	
	public ResponseEntity<String> healthCheck();*/
}
