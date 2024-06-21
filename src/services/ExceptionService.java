package services;

public class ExceptionService extends Error
{
    public ExceptionService(String msg) 
    {
        super(msg);
    }
}