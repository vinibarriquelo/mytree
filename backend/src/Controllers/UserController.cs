using Microsoft.AspNetCore.Mvc;

namespace backend.Controllers;

[ApiController]
[Route("user")]
public class UserController : ControllerBase
{
    private UserService _userService;
    
    public UserController(UserService userService)
    {
        _userService = userService;
    }
    
    [HttpPost]
    public async Task<ActionResult> CreateUser([FromBody] CreateUserDTO payload)
    {
        var result = new CreateUserDTOValidator().Validate(payload);
        
        if (!result.IsValid)
            return BadRequest(result.Errors);
            
        User createdUser = await _userService.CreateUser(payload);

        return Created("", createdUser);
    }
    
    [HttpPut]
    public ActionResult EditUser([FromBody] CreateUserDTO payload)
    {
        return Ok();
    }
}
