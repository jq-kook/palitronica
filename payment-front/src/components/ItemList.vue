<template>
  <div>
    <ItemBox v-for="(item, index) in items" :key="index" :item="item" @changeQuantityFromItemBox="changeQuantityFromItemList" />
  </div>
</template>

<script>
import ItemBox from './ItemBox.vue'
export default {
  props: {
    items: {
      type: Array,
      required: true
    }
  },
  components: {
    ItemBox
  },
  methods: {
    changeQuantityFromItemList (itemId, quantity) {
      console.debug('changeQuantityFromItemList : itemId=' + itemId + ' quantity=' + quantity)
      this.$emit('changeQuantity', itemId, quantity)
    }
  }
}
</script>
