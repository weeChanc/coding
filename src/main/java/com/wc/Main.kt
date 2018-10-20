package com.wc

import java.io.FileOutputStream
import java.net.Socket
import java.net.URI
import java.net.URL
import java.net.URLConnection

fun main(args: Array<String>) {
    val ins = URL("https://vip.d0.baidupan.com/file/?A2VaZF1sVGUEDQI6AjdVOVtkVW1eTwRnBHVWOVRrA2MEcFBYCB4GNwgnBjdQYVQ7BAEHUgA+UCYDZ1U7BgUDdAMwWiNdN1RfBBACLQIJVRBbR1U/XlkEWQRWVgJUTQNWBCpQYggtBjcIeAY2UG1UOQQ7B10ANlA2AzlVZwZpAzEDNlo+XTlUNwR0AjICJVVpWzBVZ145BDUENFZkVDwDJAR0UHcIaAZgCG4GYVA8VHoEbgc6AHBQYgM2VXwGbwMwA2ZabF03VGQEYAJjAjNVYFs4VWBeYAQ+BDpWM1Q/AzQEZVBhCDcGawhoBmJQYlRnBG0HMABtUGcDPVViBnQDZwN7WmhdJlR1BCICIAJrVWVbNFVjXjIENwQ6VmFUPwM1BDBQIQghBjsIMwY1UGJUaARvBzcAbFBrAzBVYgZsAzYDM1o4XS5ULgR8").openStream();
    val os= FileOutputStream("C:\\Users\\c\\Desktop\\out.rar")
    os.write(ins.readBytes())
}