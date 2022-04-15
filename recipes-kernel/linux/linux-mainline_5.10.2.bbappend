FILESEXTRAPATHS:prepend:sc-gateway := "${THISDIR}/files:"

SRC_URI += "file://sun8i-h2-plus-orangepi-zero-sc-gateway.dts \
    file://add-sc-gateway-dtb-make.patch \
"

do_patch:append() {
    mv ${WORKDIR}/sun8i-h2-plus-orangepi-zero-sc-gateway.dts ${S}/arch/arm/boot/dts/sun8i-h2-plus-orangepi-zero-sc-gateway.dts
}

do_configure:append() {
    kernel_conf_variable RTC_DRV_DS1307 y
    kernel_conf_variable KEYBOARD_GPIO y
}