KERNEL_CLASSES:append:sota = " kernel-fitimage"
KERNEL_IMAGETYPE:sota = "fitImage"
INITRAMFS_FSTYPES = "cpio.gz"
OSTREE_KERNEL = "${KERNEL_IMAGETYPE}-${INITRAMFS_IMAGE}-${MACHINE}-${KERNEL_FIT_LINK_NAME}"

# Deploy config fragment list to OSTree root fs
IMAGE_INSTALL:append = " fit-conf"

OSTREE_BOOTLOADER ?= "u-boot"

WKS_FILES:sc-gateway ?= "sc-gateway-sdcard-image.wks.in"
