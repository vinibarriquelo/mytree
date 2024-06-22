using backend.Models;
using backend.Models.DTO_s;
using backend.Models.Validator;
using backend.Services;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("pages")]
public class PageController : ControllerBase
{
    private PageService _pageService;

    public PageController(PageService pageService)
    {
        _pageService = pageService;
    }

    [HttpPost]
    public async Task<ActionResult<Page>> CreatePage([FromBody] CreatePageDTO payload)
    {
        var result = new CreatePageDTOValidator().Validate(payload);

        if (!result.IsValid)
            return BadRequest(result.Errors);

        var createdPage = await _pageService.CreatePage(payload);

        return Created("", "createdPage");
    }

    [HttpGet("{userId}")]
    public async Task<ActionResult<List<ListPageByUserDTO>>> FindPagesByUserId([FromRoute] Guid userId)
    {
        var pages = await _pageService.FindPagesByUserId(userId);

        return Ok(pages);
    }
    
    [HttpPut("{pageId}")]
    public async Task<ActionResult<Page>> UpdatePage([FromRoute] Guid pageId, [FromBody] EditPageDTO payload)
    {
       var page = await _pageService.UpdatePage(payload, pageId);
       return Ok(page);
    }

    [HttpDelete("{pageId}")]
    public async Task<ActionResult> DeletePage([FromRoute] Guid pageId)
    {
        await _pageService.DeletePage(pageId);
        return Ok();
    }
}