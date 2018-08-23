package com.example.springboot2docker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger( WelcomeController.class );
	@GetMapping(value = "/api", produces = "text/plain")
	@CrossOrigin
	public String welcome() {
		String hostname = System.getenv().getOrDefault("HOSTNAME", "Unknown");
		return String.format("Hello world from %s", hostname);
	}
	
	@GetMapping(value="/api/initialMemory", produces = "application/json")
	@CrossOrigin
	public Memory initialMemory() {
		
		
		Runtime runtime = Runtime.getRuntime();
		final long maxMemory = runtime.maxMemory();
		final long usedMemory = runtime.totalMemory();
		final long freeMemory = runtime.freeMemory();
		LOGGER.info("free memory {}", freeMemory / (1024 * 1024));

		return new Memory(humanReadableByteCount(maxMemory), humanReadableByteCount((maxMemory - usedMemory)), humanReadableByteCount(usedMemory));
		
	}

	@CrossOrigin
	@GetMapping(value = "/api/allocate80", produces = "text/plain")
	public String memory() {
		Runtime rt = Runtime.getRuntime();
		StringBuilder sb = new StringBuilder();
		long maxMemory = rt.maxMemory();
		long usedMemory = 0;
		LOGGER.info("max Memory {} usedMemory {}",humanReadableByteCount(maxMemory), humanReadableByteCount(rt.totalMemory()));
		try {
			while (((float) usedMemory / maxMemory) < 0.80) {
				sb.append(System.nanoTime() + sb.toString());
				usedMemory = rt.totalMemory();
			}
		}
		finally {
			String msg = "Allocated more than 80% (" + humanReadableByteCount(usedMemory)
					+ ") of the max allowed JVM memory size (" + humanReadableByteCount(maxMemory) + ")";
			System.out.println(msg);
			return msg;
		}
	}

	public static String humanReadableByteCount(long bytes) {
		int unit = 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("KMGTPE").charAt(exp - 1) + ("i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

}
