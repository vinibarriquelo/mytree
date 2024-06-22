using FluentValidation;

public class CreateUserDTOValidator : AbstractValidator<CreateUserDTO>
{
    public CreateUserDTOValidator()
    {
        RuleFor(user => user.Name).NotEmpty().WithMessage("Nome é orbigatorio!");

        RuleFor(user => user.Email)
            .NotEmpty().WithMessage("Email é obrigatorio!")
            .EmailAddress().WithMessage("é Preiso prencher um email válido!");

        RuleFor(user => user.Password)
          .NotEmpty().WithMessage("Senha é obirgatoria!");
    }
}