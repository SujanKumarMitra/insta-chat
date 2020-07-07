package com.skmproject.chatapp.util;

import java.nio.charset.Charset;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-07-07
 */
@Component
public class SimpleRandomGenerator implements RandomGenerator {

	Random random = new Random();

	@Override
	public String generateRandom() {
		StringBuilder hash = new StringBuilder();
		long time = System.currentTimeMillis();
		hash.append(time);
		int length = hash.length();
		int randomNumber = random.nextInt(length);
		hash = reverse(hash, randomNumber, length-1);
		return Hashing.murmur3_32().hashString(hash, Charset.defaultCharset()).toString();
	}

	public StringBuilder reverse(StringBuilder str, int start, int end) {
		StringBuilder substring = new StringBuilder(str.substring(start, end + 1));
		substring.reverse();
		str.replace(start, end + 1, substring.toString());
		return str;
	}

	public static void main(String[] args) {
		System.out.println(new SimpleRandomGenerator().generateRandom());
	}

}
