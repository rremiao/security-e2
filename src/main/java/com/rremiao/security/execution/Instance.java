package com.rremiao.security.execution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

@Service
public class Instance {
    private static final String videoPath = "src/main/resources/FuncoesResumo - Hash Functions.mp4";
    private String myFile = "";

    public void run(String filePath) throws NoSuchAlgorithmException, IOException {
        if (filePath.equals("")) {
            myFile = videoPath;
        }
        else {
            myFile = filePath;
        }

        LinkedList<byte[]> pieces = fileReader(myFile);

        process(pieces);
    }

    public LinkedList<byte[]> fileReader(String path) throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(path));

        LinkedList<byte[]> pieces = HashInstance.pieceDismantle(file);

        return pieces;
    }

    public void process(LinkedList<byte[]> pieces) throws NoSuchAlgorithmException {
        byte[] lastPiece;
        byte[] h0;

        lastPiece = h0 = HashInstance.hashPiece(pieces.pollLast());

        while(pieces.size() > 0) {
            h0 = HashInstance.joinPieces(pieces.pollLast(), h0);
            h0 = HashInstance.hashPiece(h0);
        }

        printer(lastPiece, h0);
    }

    public void printer(byte[] lastPiece, byte[] h0) {
        System.out.println("Ultimo pedaço do hash:");
        System.out.println(HashInstance.hexToString(lastPiece));
        System.out.println();
        System.out.println("Primeiro pedaço do hash:");
        System.out.println(HashInstance.hexToString(h0));
        System.out.println();
    }
}