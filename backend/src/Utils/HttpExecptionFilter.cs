using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using System.Net;

public class HttpExceptionFilter : IExceptionFilter
{
    public void OnException(ExceptionContext context)
    {
        if (context.Exception is HttpException httpException)
        {
            var errorResponse = new ErrorResponse
            {
                Message = httpException.Message
            };

            context.Result = new ObjectResult(errorResponse)
            {
                StatusCode = (int)httpException.StatusCode
            };
            context.ExceptionHandled = true;
        }
    }

    private class ErrorResponse
    {
        public string Message { get; set; }
    }
}
