package net.javaguides.springboot.web.dto;


public class UserTransferDto {
  private String senderEmail;
  private String receiverEmail;
  private Long transferAmount;

  public UserTransferDto() {
  }

  public UserTransferDto(String senderEmail, String receiverEmail, Long transferAmount) {
    super();
    this.senderEmail = senderEmail;
    this.receiverEmail = receiverEmail;
    this.transferAmount = transferAmount;
  }

  public String getSenderEmail() {
    return senderEmail;
  }

  public void setSenderEmail(String senderEmail) {
    this.senderEmail = senderEmail;
  }

  public String getReceiverEmail() {
    return receiverEmail;
  }

  public void setReceiverEmail(String receiverEmail) {
    this.receiverEmail = receiverEmail;
  }

  public Long getTransferAmount() {
    return transferAmount;
  }

  public void setTransferAmount(Long transferAmount) {
    this.transferAmount = transferAmount;
  }
}
