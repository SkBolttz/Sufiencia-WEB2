package com.RestAPIFurb.WEB2.Exception.Handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.RestAPIFurb.WEB2.Exception.Comanda.ComandaNaoLocalizadaException;
import com.RestAPIFurb.WEB2.Exception.Comanda.ErroAoListarComandaException;
import com.RestAPIFurb.WEB2.Exception.Comanda.RegistroComandaException;
import com.RestAPIFurb.WEB2.Exception.Comanda.UsuarioNaoLocalizadoException;
import com.RestAPIFurb.WEB2.Exception.Produto.CadastroProdutoExecption;
import com.RestAPIFurb.WEB2.Exception.Produto.DuplicidadeCadastroException;
import com.RestAPIFurb.WEB2.Exception.Produto.ProdutoNaoLocalizadoException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CadastroProdutoExecption.class)
    public ResponseEntity<ErrorResponse> handleCadastroProdutoExecption(CadastroProdutoExecption ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErro(), ex.getDetalhe());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(RegistroComandaException.class)
    public ResponseEntity<ErrorResponse> handleCadastroProdutoExecption(RegistroComandaException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErro(), ex.getDetalhe());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(DuplicidadeCadastroException.class)
    public ResponseEntity<ErrorResponse> handleCadastroProdutoExecption(DuplicidadeCadastroException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErro(), ex.getDetalhe());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(UsuarioNaoLocalizadoException.class)
    public ResponseEntity<ErrorResponse> handleCadastroProdutoExecption(UsuarioNaoLocalizadoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErro(), ex.getDetalhe());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(ProdutoNaoLocalizadoException.class)
    public ResponseEntity<ErrorResponse> handleCadastroProdutoExecption(ProdutoNaoLocalizadoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErro(), ex.getDetalhe());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(ComandaNaoLocalizadaException.class)
    public ResponseEntity<ErrorResponse> handleCadastroProdutoExecption(ComandaNaoLocalizadaException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErro(), ex.getDetalhe());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(ErroAoListarComandaException.class)
    public ResponseEntity<ErrorResponse> handleCadastroProdutoExecption(ErroAoListarComandaException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErro(), ex.getDetalhe());
        return ResponseEntity.status(400).body(errorResponse);
    }
}
