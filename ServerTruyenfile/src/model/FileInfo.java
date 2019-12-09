package model;
import java.io.Serializable;
public class FileInfo implements Serializable {
	 private String destinationDirectory;
	    
	    private String filename;
	    private long fileSize;
	    private byte[] dataBytes;
	    public FileInfo() {
			super();
		}
		private String status;
		public String getDestinationDirectory() {
			return destinationDirectory;
		}
		public void setDestinationDirectory(String destinationDirectory) {
			this.destinationDirectory = destinationDirectory;
		}
		
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public long getFileSize() {
			return fileSize;
		}
		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}
		public byte[] getDataBytes() {
			return dataBytes;
		}
		public void setDataBytes(byte[] dataBytes) {
			this.dataBytes = dataBytes;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public FileInfo(String destinationDirectory, String sourceDirectory, String filename, long fileSize,
				byte[] dataBytes, String status) {
			super();
			this.destinationDirectory = destinationDirectory;
			
			this.filename = filename;
			this.fileSize = fileSize;
			this.dataBytes = dataBytes;
			this.status = status;
		}
}
