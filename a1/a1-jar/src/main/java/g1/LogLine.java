package g1;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

@JsonAutoDetect
class LogLine {
  @JsonProperty
  String date;
  @JsonProperty
  String hostinfo;
  @JsonProperty
  String msg;
  @JsonProperty
  String logLevel;

  public static SimpleDateFormat DATEFORMAT_WITH_ERA = new java.text.SimpleDateFormat("GGyyyy-MM-dd");//could add: " HH:mm:ss:SSS zzz");

  LogLine(String msgIn, String levelIn) throws UnknownHostException, SocketException {
    this.date = DATEFORMAT_WITH_ERA.format(new java.util.Date(System.currentTimeMillis()));
    this.hostinfo = InetAddress.getLocalHost().getCanonicalHostName() + " " + getIpAddresses();
    this.msg = msgIn;
    this.logLevel = levelIn;
  }
  //
  //@Override
  //public String toString() {
  //  return logLevel + ":" + new Date(date) + "/" + ip + ": " + msg;
  //}

  public static String getIpAddresses() throws SocketException {
    String addressesStr = "";
    Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
    while (ifaces.hasMoreElements()) {
      NetworkInterface iface = ifaces.nextElement();
      Enumeration<InetAddress> addresses = iface.getInetAddresses();
      while (addresses.hasMoreElements()) {
        InetAddress address = addresses.nextElement();
        // Filter out all of the IPv6 addresses
        if (!(address instanceof Inet6Address)) {
          String addressStr = address.getHostAddress();
          if (!(addressStr.startsWith("127.") || addressStr.startsWith("0:0:"))) {
            if (addressesStr.length() > 0) {
              addressesStr += " ";
            }
            addressesStr += addressStr;
          }
        }
      }
    }
    return addressesStr;
  }
}
