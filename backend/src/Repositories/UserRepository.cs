using database;
using Microsoft.EntityFrameworkCore;

public class UserRepository
{
  
  private AppDbContext _context;
  
  public UserRepository(AppDbContext context)
  {
    _context = context;
  }
  
  public async Task CreateUser(User user)
  {
    await _context.Users.AddAsync(user);
    await _context.SaveChangesAsync();
    return;
  }
  
  public async Task<Boolean> CheckEmail(string email)
  {
    return await _context.Users.AnyAsync(u => u.Email == email);
  }

  public async Task<User> FindUserById(Guid userId)
  {
    User user = await _context.Users.FindAsync(userId);
    return user;
  }
}