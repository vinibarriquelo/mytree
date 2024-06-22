using backend.Repositories;
using backend.Services;
using database;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers(options =>
{
    options.Filters.Add(typeof(HttpExceptionFilter));
});

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Database.
builder.Services.AddScoped<AppDbContext>();

// User providers.
builder.Services.AddScoped<UserService>();
builder.Services.AddScoped<UserRepository>();

// Page providers.
builder.Services.AddScoped<PageService>();
builder.Services.AddScoped<PageRepository>();

// Link providers.
builder.Services.AddScoped<LinkService>();
builder.Services.AddScoped<LinkRepository>();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
