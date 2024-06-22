using backend.Models;
using Microsoft.EntityFrameworkCore;

namespace database
{
    public class AppDbContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<Page> Pages { get; set; }
        public DbSet<Link> Links { get; set; }
        

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseNpgsql("Host=localhost;Database=mytree;Username=postgres;Password=12345");

        }
        
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>().Property(u => u.Id).HasDefaultValueSql("uuid_generate_v4()");
            modelBuilder.Entity<Page>().Property(u => u.Id).HasDefaultValueSql("uuid_generate_v4()");
            modelBuilder.Entity<Link>().Property(u => u.Id).HasDefaultValueSql("uuid_generate_v4()");

            base.OnModelCreating(modelBuilder);
        }
    }
}
