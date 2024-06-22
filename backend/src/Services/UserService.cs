using System.Net;
using Microsoft.AspNetCore.Http.HttpResults;

public class UserService
{
  
  private UserRepository _userRepository;
  
  public UserService(UserRepository userRepository)
  {
    _userRepository = userRepository;
  }
  
  public async Task<User> CreateUser(CreateUserDTO payload)
  {
    if (await _userRepository.CheckEmail(payload.Email))
      throw new HttpException(HttpStatusCode.Conflict, "Já existe um usuario com este email!");
    
    string hashedPassword = BCrypt.Net.BCrypt.HashPassword(payload.Password);
    
    var user = new User(payload.Name, payload.Email, hashedPassword);
    await _userRepository.CreateUser(user);
    return user;
  }

  public async Task<User> FindUserById(Guid userId)
  {
    var user = await _userRepository.FindUserById(userId);
    return user;
  }
}