package com.rremiao.security.execution;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

@Service
public class HashInstance {
    private static final int pieceByteSize = 1024;

    public static LinkedList<byte[]> pieceDismantle(byte[] origin) {
        LinkedList<byte[]> list = new LinkedList<byte[]>();

        for (int i = 0, block = 0; i < origin.length; i += pieceByteSize) {
            block = Math.min(pieceByteSize, origin.length - i);
            byte[] piece = new byte[block];

            System.arraycopy(origin, i, piece, 0, block);
            list.add(piece);
        }

        return list;
    }

    public static byte[] hashPiece(byte[] lastPiece) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(lastPiece);
    }

    public static byte[] joinPieces(byte[] start, byte[] finish) {
        byte[] joinPoint = new byte[start.length + finish.length];

        System.arraycopy(start, 0, joinPoint, 0, start.length);
        System.arraycopy(finish, 0, joinPoint, start.length, finish.length);

        return joinPoint;
    }

    public static String hexToString(byte[] stringBytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte myByte : stringBytes) {
            stringBuilder.append(String.format("%02x ", myByte));
        }

        return stringBuilder.toString();
    }
}
