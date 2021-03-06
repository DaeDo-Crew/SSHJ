package sshj.sshj.controller;

import java.util.List;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;
import sshj.sshj.dto.ReviewDto;
import sshj.sshj.dto.UserHeaderModel;
import sshj.sshj.service.ReviewService;

@Api(value="ReviewController", description="ReviewController")
@RequestMapping("/review")
@RestController
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;

    @ApiOperation(
            value = "후기 생성"
            , notes = "후기 생성"
            ,authorizations = {@Authorization(value = "JWT")}
    )
    @ApiResponses(value={
            @ApiResponse(code=200, message="")
    })
    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public ResponseEntity<Void> createReview(@ApiParam(value = "미팅 id", required = true) @RequestParam(name = "meeting_id", required = true) long meetingId,
                                             @ApiParam(value = "후기", required = true) @RequestParam(name = "content", required = true) String content,
                                             @ApiIgnore @RequestAttribute("UserHeaderInfo") UserHeaderModel userHeaderModel) throws Exception{
        
    	reviewService.createReview(meetingId, userHeaderModel.getUserId(), content);
    	
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "후기 리스트 조회"
            , notes = "후기 리스트 조회"
            ,authorizations = {@Authorization (value = "JWT")}
    )
    @ApiResponses(value={
            @ApiResponse(code=200, message="")
    })
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ResponseEntity<List<ReviewDto>> getReviewList(
    		@ApiParam(value = "모임 번호") @RequestParam("meetingId")long meetingId
    		, @RequestParam("page")int page, @RequestParam("size")int size ) throws Exception{
        
    	List<ReviewDto> reviewList = reviewService.getReviewList(meetingId, page, size); 
    	
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "유저 후기 수정"
            , notes = "유저 후기 수정"
            ,authorizations = {@Authorization (value = "JWT")}
    )
    @ApiResponses(value={
            @ApiResponse(code=200, message="")
    })
    @RequestMapping(value = "/update", method= RequestMethod.PATCH)
    public ResponseEntity<Void> updateReview(
    		@ApiIgnore @RequestAttribute("userId") Long userId, 
    		@ApiParam(value = "후기 번호") @RequestParam("reviewId")long reviewId,
    		@ApiParam(value = "후기 내용") @RequestParam("content")String content) throws Exception{
        
    	reviewService.updateReview(userId, reviewId, content);
    	
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation(
            value = "유저 후기 삭제"
            , notes = "유저 후기 삭제"
            ,authorizations = {@Authorization (value = "JWT")}
    )
    @ApiResponses(value={
            @ApiResponse(code=200, message="")
    })
    @RequestMapping(value = "/delete", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReview(
    		@ApiIgnore @RequestAttribute("userId") Long userId, 
    		@ApiParam(value = "후기 번호") @RequestParam("reviewId")long reviewId) throws Exception{
        
    	reviewService.deleteReview(userId, reviewId);
    	
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
