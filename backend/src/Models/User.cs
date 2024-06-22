using System.ComponentModel.DataAnnotations;

public class User
{
  [Key]
  public Guid Id { get; private set; }
  public string Name {get; private set;}
  public string Email {get; private set;}
  public string Password {get; private set;}

  public User()
  {
    
  }
  
  public User (string name, string email, string password)
  {
    Name = name;
    Email = email;
    Password = password;
  }
}