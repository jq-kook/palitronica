<template>
  <section class="rounded width-300">
    <b-container>
      <b-row>
        <b-col style="font-size:13px;font-weight: bold;text-align:start;" align-self="center">{{ item.name }}</b-col>
        <b-col cols="4">$ {{ item.price }}</b-col>
      </b-row>
      <b-row>
        <b-col style="text-align:start;">
          <img :src="item.imgUrl" height="80" width="60"/>
        </b-col>
        <b-col cols="4">
          <b-form-input
            v-model="quantity"
            type="number"
            min="0"
            max="50"
            @change="changeQuantityFromItemBox()"
          />
        </b-col>
      </b-row>
    </b-container>
  </section>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
      required: true
    }
  },
  data: function () {
    return {
      quantity: 0
    }
  },
  methods: {
    changeQuantityFromItemBox () {
      console.debug(
        'changeQuantityFromItemBox : itemId=' +
          this.item.itemId +
          ' quantity=' +
          this.quantity
      )
      this.$emit('changeQuantityFromItemBox', this.item.itemId, this.quantity)
    }
  }
}
</script>

<style scoped>
.rounded {
  border-radius: 15px;
  background: #F5F5F5;
  height: 120px;
  margin: 5px;
  padding: 7px;
}
.width-300 {
  width: 300px;
}
.flex {
  display: flex;
}
.flex-1 {
  flex: 1;
}
.flex-05 {
  flex: 0.5;
}
.width-50 {
  width: 50px;
}
.padding-5 {
  padding: 5px;
}
@media (max-width: 700px) {
  .width-300 {
    width: auto;
  }
}
</style>
