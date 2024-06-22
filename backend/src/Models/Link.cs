using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace backend.Models;

public class Link
{
    [Key]
    public Guid Id { get;  set; }
    
    public string Title { get;  set; }
    
    public string RedirectLink { get;  set; }
    
    [ForeignKey("PageId")]
    public Page Page { get; set; }
    
    // Icon
}