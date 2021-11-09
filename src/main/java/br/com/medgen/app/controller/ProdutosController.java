package br.com.isaccanedo.app.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosController {

    @PostMapping
    public String criaProduto(){
        return "Produto criado";
    }

    @GetMapping
    public String getProdutos() throws Exception{

        int contadorPedidos = 0;
        int contadorArquivos = 0;

            OutputStream outputStream = getOutPutStream(contadorArquivos);
            JsonGenerator jsonGenerator = getJsonGenerator(outputStream);

            for (int c = 0; c < 501; c++) {
                if(contadorPedidos < 100) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeNumberField("numero", c);
                    jsonGenerator.writeEndObject();
                    contadorPedidos++;
                } else {
                    jsonGenerator.close();
                    outputStream.close();
                    break;
                }
            }

            jsonGenerator.close();
            outputStream.close();

            contadorPedidos = 0;
            contadorArquivos++;

        return "Todos os produtos";

    }


    public String gerarNomeArquivo(int contador){
        return "arquivo-" + contador;
    }

    public OutputStream getOutPutStream(int contadorPedidos) throws Exception{
        final File ARQUIVO = new File("./" + gerarNomeArquivo(contadorPedidos) + ".json");
        final OutputStream outputStream = new FileOutputStream(ARQUIVO);
        return outputStream;
    }

    public JsonGenerator getJsonGenerator(OutputStream outputStream) throws Exception{
        final JsonFactory jsonFactory = new JsonFactory();
        final JsonGenerator jsonGenerator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
        return jsonGenerator.setCodec(new ObjectMapper());
    }

    @GetMapping("/{id}")
    public String getProdutoPorId(@PathVariable int id){
        return "Produto " + id;
    }

    @PutMapping("/{id}")
    public String atualizaProduto(@PathVariable int id){
        return "Produto " + id + " atualizado";
    }

    @DeleteMapping("/{id}")
    public String deleteProduto(@PathVariable int id){
        return "Produto " + id + " deletado";
    }

}
