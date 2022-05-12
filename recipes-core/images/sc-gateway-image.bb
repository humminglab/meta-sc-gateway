SUMMARY = "SC Gateway Image"
# networkmanager-nmcli
# networkmanager-nmtui

IMAGE_INSTALL = "packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    ${IMAGE_UTILS_INSTALL} \
    kernel-module-ccm \
    xradio \
    iw \ 
    wpa-supplicant \
    sc-conf \
    go-sc-gateway \
    "

IMAGE_UTILS_INSTALL = " \
    strace \
    dropbear \
    i2c-tools \
    picocom \
    "

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "11264"
IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"

MACHINE_EXTRA_RRECOMMENDS = " kernel-modules"
