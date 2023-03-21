SUMMARY = "SC Gateway Image"
# networkmanager-nmcli
# networkmanager-nmtui

# remove aktualizr including in sota.bbclass
IMAGE_INSTALL:remove += "aktualizr aktualizr-info ${SOTA_CLIENT_PROV}"

IMAGE_INSTALL = "packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    ${IMAGE_UTILS_INSTALL} \
    kernel-modules \
    xradio \
    iw \ 
    wpa-supplicant \
    sc-conf \
    go-sc-gateway \
    tzdata \
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
