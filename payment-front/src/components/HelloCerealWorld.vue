<template>
  <div class="contentWidth">
    <h2>Hello Cereal World</h2>
    <b-form-select v-model="selectedCustomerId" @change="onSelectChange($event)" size="sm" style="width:200px">
      <b-form-select-option disabled value="">Please select customer</b-form-select-option>
      <b-form-select-option v-for="customer in customers" v-bind:key="customer.customerId" v-bind:value="customer.customerId">
        {{ customer.name }}
      </b-form-select-option>
    </b-form-select>
    <p v-if="showShippingAddress" class="shipping">Shipping address: {{ address }}</p>
    <div class="flex">
      <div class="pane">
        <h4>Item</h4>
        <item-list :items="items" @changeQuantity="changeQuantity"/>
      </div>
      <div class="pane">
        <h4>Purchase Info</h4>
        <textarea class="textarea textareaHeight" v-model="purchaseInfo" />
      </div>
    </div>
    <b-button variant="primary" @click="onSubmit">Purchase Submit</b-button>
  </div>
</template>

<script>
import ItemModelForRequest from '../model/ItemModelForRequest.js'
import ItemList from './ItemList.vue'
import axios from 'axios'
export default {
  components: {
    ItemList
  },
  name: 'HelloWorld',
  data () {
    return {
      customers: [],
      items: [],
      selectedCustomerId: '',
      quantityObject: {},
      purchaseInfo: '',
      address: '',
      dismissSecs: 100,
      dismissCountDown: 0,
      showDismissibleAlert: false
    }
  },
  methods: {
    getCustomerList () {
      axios.get(process.env.BACKEND_ADDR + '/customers').then(
        response => (
          this.customers = response.data.map((customer) => ({
            city: customer.city,
            countryCode: customer.countryCode,
            customerId: customer.customerId,
            name: customer.name,
            zipCode: customer.zipCode
          }))
        ))
        .catch(error => console.log(error))
    },
    getItemList () {
      axios.get(process.env.BACKEND_ADDR + '/items').then(
        response => {
          this.items = response.data.map((item) => ({
            itemId: item.itemId,
            name: item.name,
            price: item.price,
            imgUrl: item.imgUrl
          }))
          this.items.forEach(item => {
            this.quantityObject[item.itemId] = 0
          })
        })
        .catch(error => console.log(error))
    },
    onSubmit (event) {
      this.purchaseInfo = ''
      if (this.selectedCustomerId === '') {
        this.purchaseInfo =
              '!!!!!!!!!!!!!!!' + '\n' +
              'Please select customer' + '\n' +
              '!!!!!!!!!!!!!!!'
        return
      }
      console.log(this.quantityObject)
      var requestBody = []
      for (const item of this.items) {
        if (this.quantityObject[item.itemId] < 0 || this.quantityObject[item.itemId] > 50) {
          this.purchaseInfo =
              '!!!!!!!!!!!!!!!' + '\n' +
              '0 ≤ quantity ≤ 50' + '\n' +
              '!!!!!!!!!!!!!!!'
          return
        }
        requestBody.push(new ItemModelForRequest(item.itemId, item.name, item.price, this.quantityObject[item.itemId]))
      }
      const url = process.env.BACKEND_ADDR + '/purchaseinfos/' + this.selectedCustomerId

      axios.post(url, requestBody).then(
        response => (
          this.createPurchaseInfo(response.data)
        ))
        .catch(error => {
          if (error.response) {
            this.purchaseInfo =
              '!!!!!!!!!!!!!!!' + '\n' +
              error.response.data.message + '\n' +
              '!!!!!!!!!!!!!!!'
          }
        })
    },
    changeQuantity (itemId, quantity) {
      console.debug('changeQuantity : itemId=' + itemId + ' quantity=' + quantity)
      this.quantityObject[itemId] = quantity
    },
    createPurchaseInfo (data) {
      var itemArea = ''
      data.items.forEach(item => {
        if (item.quantity !== 0) {
          itemArea = itemArea + '\n' +
            item.name + ' $' + item.price + ' * ' + item.quantity + ' = $' + item.subtotal
        }
      })
      this.purchaseInfo = 'Customer Name : ' + data.customerName + '\n' + itemArea + '\n' +
        '----------------------' + '\n' +
        'Tax Rate    :  ' + data.taxRate * 100 + '%' + '\n' +
        'Total Tax   : $' + data.totalTax + '\n' +
        '======================' + '\n' +
        'Total Price : $' + data.totalPrice
    },
    onSelectChange (value) {
      for (const customer of this.customers) {
        if (customer.customerId === value) {
          this.address = customer.city + ', ' + customer.countryCode + ', zip: ' + customer.zipCode
          break
        } else {
          this.address = ''
        }
      }
    },
    countDownChanged (dismissCountDown) {
      this.dismissCountDown = dismissCountDown
    },
    showAlert () {
      this.dismissCountDown = this.dismissSecs
    }
  },
  mounted () {
    this.getCustomerList()
    this.getItemList()
  },
  computed: {
    showShippingAddress () {
      if (this.selectedCustomerId === '') {
        return false
      } else {
        return true
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.flex {
  display: flex;
}
.pane {
  flex: 1;
  margin: 5px 10px 5px 10px;
}
.textarea {
  border:1px solid black;
  width: 90%;
  margin: 5px 10px 5px 10px;
  font-size: 13px;
}
.textareaHeight {
  height: 90%;
}
.contentWidth {
  width: 700px;
}
.shipping {
  margin: 5px;
  font-size: 13px;
}

@media (max-width: 700px) {
  .flex {
    display: flex;
    flex-direction: column;
  }
  .contentWidth {
    width: 100%;
  }
  .textareaHeight {
    height: 230px;
  }
}
</style>
