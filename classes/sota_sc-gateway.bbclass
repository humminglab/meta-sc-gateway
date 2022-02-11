KERNEL_CLASSES:append:sota = " kernel-fitimage"
KERNEL_IMAGETYPE:sota = "fitImage"
INITRAMFS_FSTYPES = "cpio.gz"
OSTREE_KERNEL = "${KERNEL_IMAGETYPE}-${INITRAMFS_IMAGE}-${MACHINE}-${KERNEL_FIT_LINK_NAME}"

OSTREE_BOOTLOADER ?= "u-boot"

WKS_FILES:sc-gateway ?= "sc-gateway-sdcard-image.wks.in"
