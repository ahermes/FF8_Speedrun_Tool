//package com.speedrun.cardrng.chrono.engine;
//
//public class pingvaleStudy {
//
//	$option = {
//	  language: :en,
//	  
//	  base: 550,
//	  
//	  width: 400,
//	  
//	  recovery_width: 360,
//	  
//	  counting_width: 100,
//	  
//	  counting_frame_width: 40,
//	  
//	  early_quistis: :pingval,
//	  
//	  autofire_speed: 12,
//	  
//	  delay_frame: 69,
//	  
//	  ranks_order: "ulrd",
//	  
//	  strong_highlight_cards: [105],
//	  
//	  highlight_cards: [21, 48, 53],
//	  
//	  order: :reverse,
//	  
//	  console_fps: 60,
//	  
//	  
//	  player: :zellmama,
//	  
//	  fuzzy: %w[. r o ro],
//	  
//	  forced_incr: 10,
//	  
//	  accept_delay_frame: 2,
//
//	  prompt: "> ",
//	  
//	  debug: false,
//	  fallback_language: :en,
//	}
//
//	Default_Ranks_Order = "urdl"
//
//	Early_Quistis_State_Table = {
//	  luzbelheim: {
//	    unused:   0x0000_0001,      
//	    elastoid: 0x1340_eb2b,      
//	    malboro:  0x5f22_3d12,      
//	    wedge:    0x1f13_2481,     
//	  },
//	  pingval: {
//	    unused:   0x0000_0001,     
//	    elastoid: 0x1de5_b942,     
//	    malboro:  0x963c_b5e4,      
//	    wedge:    0x1f13_2481,      
//	  },
//	}
//
//	Quistis_ID = 103                
//	Zell_ID = 105                   
//	Angelo_ID = 77                 
//	Pupu_ID = 47                    
//
//	Deck_MAX = 5
//
//	module FF8
//	  class Card_RNG < Enumerator::Lazy
//
//	    Initial_State = 0x0000_0001
//	    attr_reader :state, :seed
//	    attr_accessor :true_rand, :enum_method
//
//	    def initialize(seed=Initial_State, enum_method: :get, true_rand: false)
//	      init(seed)
//	      @enum_method = enum_method
//	      @true_rand = true_rand
//	    end
//
//	    def init(seed=nil)
//	      @seed = (seed.nil? ? rand(0..0xffff_ffff) : seed)
//	      @state = @seed
//	    end
//
//	    def shuffle
//	      init
//	    end
//
//	    def next_state
//	      return rand(0..0xffff_ffff) if @true_rand
//	      @state = (@state * 0x10dcd + 1) & 0xffff_ffff
//	    end
//
//	    def nxt
//	      next_state >> 17
//	    end
//	    alias get nxt
//
//	    def each
//	      return to_enum if !block_given?
//	      
//	      Enumerator.new {|y|
//	        loop { y << self.send(@enum_method) }
//	      }.each {|rnd|
//	        yield rnd
//	      }
//	    end
//
//	    def peek
//	      _state = @state
//	      self.send(@enum_method).tap { @state = _state }
//	    end
//
//	    def size
//	      Float::INFINITY
//	    end
//
//	    def rewind
//	      @state = @seed
//	    end
//	  end
//	  
//	  Card_Table = [
//	    {
//	      id: 0, urdl: [1, 4, 1, 5], urdl_s: "1415", level: 1, row: 1,
//	      name: "", name_e: "Geezard", element: :none, rarep: false,
//	    },
//	    {
//	      id: 1, urdl: [5, 1, 1, 3], urdl_s: "5113", level: 1, row: 2,
//	      name: "", name_e: "Funguar", element: :none, rarep: false,
//	    },
//	    {
//	      id: 2, urdl: [1, 3, 3, 5], urdl_s: "1335", level: 1, row: 3,
//	      name: "", name_e: "Bite Bug", element: :none, rarep: false,
//	    },
//	    {
//	      id: 3, urdl: [6, 1, 1, 2], urdl_s: "6112", level: 1, row: 4,
//	      name: "", name_e: "Red Bat", element: :none, rarep: false,
//	    },
//	    {
//	      id: 4, urdl: [2, 3, 1, 5], urdl_s: "2315", level: 1, row: 5,
//	      name: "", name_e: "Blobra", element: :none, rarep: false,
//	    },
//	    {
//	      id: 5, urdl: [2, 1, 4, 4], urdl_s: "2144", level: 1, row: 6,
//	      name: "", name_e: "Gayla", element: :thunder, rarep: false,
//	    },
//	    {
//	      id: 6, urdl: [1, 5, 4, 1], urdl_s: "1541", level: 1, row: 7,
//	      name: "", name_e: "Gesper", element: :none, rarep: false,
//	    },
//	    {
//	      id: 7, urdl: [3, 5, 2, 1], urdl_s: "3521", level: 1, row: 8,
//	      name: "", name_e: "Fastitocalon-F", element: :earth, rarep: false,
//	    },
//	    {
//	      id: 8, urdl: [2, 1, 6, 1], urdl_s: "2161", level: 1, row: 9,
//	      name: "", name_e: "Blood Soul", element: :none, rarep: false,
//	    },
//	    {
//	      id: 9, urdl: [4, 2, 4, 3], urdl_s: "4243", level: 1, row: 10,
//	      name: "", name_e: "Caterchipillar", element: :none, rarep: false,
//	    },
//	    {
//	      id: 10, urdl: [2, 1, 2, 6], urdl_s: "2126", level: 1, row: 11,
//	      name: "", name_e: "Cockatrice", element: :thunder, rarep: false,
//	    },
//	    {
//	      id: 11, urdl: [7, 1, 3, 1], urdl_s: "7131", level: 2, row: 1,
//	      name: "", name_e: "Grat", element: :none, rarep: false,
//	    },
//	    {
//	      id: 12, urdl: [6, 2, 2, 3], urdl_s: "6223", level: 2, row: 2,
//	      name: "", name_e: "Buel", element: :none, rarep: false,
//	    },
//	    {
//	      id: 13, urdl: [5, 3, 3, 4], urdl_s: "5334", level: 2, row: 3,
//	      name: "", name_e: "Mesmerize", element: :none, rarep: false,
//	    },
//	    {
//	      id: 14, urdl: [6, 1, 4, 3], urdl_s: "6143", level: 2, row: 4,
//	      name: "", name_e: "Glacial Eye", element: :ice, rarep: false,
//	    },
//	    {
//	      id: 15, urdl: [3, 4, 5, 3], urdl_s: "3453", level: 2, row: 5,
//	      name: "", name_e: "Belhelmel", element: :none, rarep: false,
//	    },
//	    {
//	      id: 16, urdl: [5, 3, 2, 5], urdl_s: "5325", level: 2, row: 6,
//	      name: "", name_e: "Thrustaevis", element: :wind, rarep: false,
//	    },
//	    {
//	      id: 17, urdl: [5, 1, 3, 5], urdl_s: "5135", level: 2, row: 7,
//	      name: "", name_e: "Anacondaur", element: :poison, rarep: false,
//	    },
//	    {
//	      id: 18, urdl: [5, 2, 5, 2], urdl_s: "5252", level: 2, row: 8,
//	      name: "", name_e: "Creeps", element: :thunder, rarep: false,
//	    },
//	    {
//	      id: 19, urdl: [4, 4, 5, 2], urdl_s: "4452", level: 2, row: 9,
//	      name: "", name_e: "Grendel", element: :thunder, rarep: false,
//	    },
//	    {
//	      id: 20, urdl: [3, 2, 1, 7], urdl_s: "3217", level: 2, row: 10,
//	      name: "", name_e: "Jelleye", element: :none, rarep: false,
//	    },
//	    {
//	      id: 21, urdl: [5, 2, 5, 3], urdl_s: "5253", level: 2, row: 11,
//	      name: "", name_e: "Grand Mantis", element: :none, rarep: false,
//	    },
//	    {
//	      id: 22, urdl: [6, 6, 3, 2], urdl_s: "6632", level: 3, row: 1,
//	      name: "", name_e: "Forbidden", element: :none, rarep: false,
//	    },
//	    {
//	      id: 23, urdl: [6, 3, 1, 6], urdl_s: "6316", level: 3, row: 2,
//	      name: "", name_e: "Armadodo", element: :earth, rarep: false,
//	    },
//	    {
//	      id: 24, urdl: [3, 5, 5, 5], urdl_s: "3555", level: 3, row: 3,
//	      name: "", name_e: "Tri-Face", element: :poison, rarep: false,
//	    },
//	    {
//	      id: 25, urdl: [7, 5, 1, 3], urdl_s: "7513", level: 3, row: 4,
//	      name: "", name_e: "Fastitocalon", element: :water, rarep: false,
//	    },
//	    {
//	      id: 26, urdl: [7, 1, 5, 3], urdl_s: "7153", level: 3, row: 5,
//	      name: "", name_e: "Snow Lion", element: :ice, rarep: false,
//	    },
//	    {
//	      id: 27, urdl: [5, 6, 3, 3], urdl_s: "5633", level: 3, row: 6,
//	      name: "", name_e: "Ochu", element: :none, rarep: false,
//	    },
//	    {
//	      id: 28, urdl: [5, 6, 2, 4], urdl_s: "5624", level: 3, row: 7,
//	      name: "", name_e: "SAM08G", element: :fire, rarep: false,
//	    },
//	    {
//	      id: 29, urdl: [4, 4, 7, 2], urdl_s: "4472", level: 3, row: 8,
//	      name: "", name_e: "Death Claw", element: :fire, rarep: false,
//	    },
//	    {
//	      id: 30, urdl: [6, 2, 6, 3], urdl_s: "6263", level: 3, row: 9,
//	      name: "", name_e: "Cactuar", element: :none, rarep: false,
//	    },
//	    {
//	      id: 31, urdl: [3, 6, 4, 4], urdl_s: "3644", level: 3, row: 10,
//	      name: "", name_e: "Tonberry", element: :none, rarep: false,
//	    },
//	    {
//	      id: 32, urdl: [7, 2, 3, 5], urdl_s: "7235", level: 3, row: 11,
//	      name: "", name_e: "Abyss Worm", element: :earth, rarep: false,
//	    },
//	    {
//	      id: 33, urdl: [2, 3, 6, 7], urdl_s: "2367", level: 4, row: 1,
//	      name: "", name_e: "Turtapod", element: :none, rarep: false,
//	    },
//	    {
//	      id: 34, urdl: [6, 5, 4, 5], urdl_s: "6545", level: 4, row: 2,
//	      name: "", name_e: "Vysage", element: :none, rarep: false,
//	    },
//	    {
//	      id: 35, urdl: [4, 6, 2, 7], urdl_s: "4627", level: 4, row: 3,
//	      name: "", name_e: "T-Rexaur", element: :none, rarep: false,
//	    },
//	    {
//	      id: 36, urdl: [2, 7, 6, 3], urdl_s: "2763", level: 4, row: 4,
//	      name: "", name_e: "Bomb", element: :fire, rarep: false,
//	    },
//	    {
//	      id: 37, urdl: [1, 6, 4, 7], urdl_s: "1647", level: 4, row: 5,
//	      name: "", name_e: "Blitz", element: :thunder, rarep: false,
//	    },
//	    {
//	      id: 38, urdl: [7, 3, 1, 6], urdl_s: "7316", level: 4, row: 6,
//	      name: "", name_e: "Wendigo", element: :none, rarep: false,
//	    },
//	    {
//	      id: 39, urdl: [7, 4, 4, 4], urdl_s: "7444", level: 4, row: 7,
//	      name: "", name_e: "Torama", element: :none, rarep: false,
//	    },
//	    {
//	      id: 40, urdl: [3, 7, 3, 6], urdl_s: "3736", level: 4, row: 8,
//	      name: "", name_e: "Imp", element: :none, rarep: false,
//	    },
//	    {
//	      id: 41, urdl: [6, 2, 7, 3], urdl_s: "6273", level: 4, row: 9,
//	      name: "", name_e: "Blue Dragon", element: :poison, rarep: false,
//	    },
//	    {
//	      id: 42, urdl: [4, 5, 5, 6], urdl_s: "4556", level: 4, row: 10,
//	      name: "", name_e: "Adamantoise", element: :earth, rarep: false,
//	    },
//	    {
//	      id: 43, urdl: [7, 5, 4, 3], urdl_s: "7543", level: 4, row: 11,
//	      name: "", name_e: "Hexadragon", element: :fire, rarep: false,
//	    },
//	    {
//	      id: 44, urdl: [6, 5, 6, 5], urdl_s: "6565", level: 5, row: 1,
//	      name: "", name_e: "Iron Giant", element: :none, rarep: false,
//	    },
//	    {
//	      id: 45, urdl: [3, 6, 5, 7], urdl_s: "3657", level: 5, row: 2,
//	      name: "", name_e: "Behemoth", element: :none, rarep: false,
//	    },
//	    {
//	      id: 46, urdl: [7, 6, 5, 3], urdl_s: "7653", level: 5, row: 3,
//	      name: "", name_e: "Chimera", element: :water, rarep: false,
//	    },
//	    {
//	      id: 47, urdl: [3, 10, 2, 1], urdl_s: "3a21", level: 5, row: 4,
//	      name: "", name_e: "PuPu", element: :none, rarep: false,
//	    },
//	    {
//	      id: 48, urdl: [6, 2, 6, 7], urdl_s: "6267", level: 5, row: 5,
//	      name: "", name_e: "Elastoid", element: :none, rarep: false,
//	    },
//	    {
//	      id: 49, urdl: [5, 5, 7, 4], urdl_s: "5574", level: 5, row: 6,
//	      name: "", name_e: "GIM47N", element: :none, rarep: false,
//	    },
//	    {
//	      id: 50, urdl: [7, 7, 4, 2], urdl_s: "7742", level: 5, row: 7,
//	      name: "", name_e: "Malboro", element: :poison, rarep: false,
//	    },
//	    {
//	      id: 51, urdl: [7, 2, 7, 4], urdl_s: "7274", level: 5, row: 8,
//	      name: "", name_e: "Ruby Dragon", element: :fire, rarep: false,
//	    },
//	    {
//	      id: 52, urdl: [5, 3, 7, 6], urdl_s: "5376", level: 5, row: 9,
//	      name: "", name_e: "Elnoyle", element: :none, rarep: false,
//	    },
//	    {
//	      id: 53, urdl: [4, 6, 7, 4], urdl_s: "4674", level: 5, row: 10,
//	      name: "", name_e: "Tonberry King", element: :none, rarep: false,
//	    },
//	    {
//	      id: 54, urdl: [6, 6, 2, 7], urdl_s: "6627", level: 5, row: 11,
//	      name: "", name_e: "Wedge Biggs", element: :none, rarep: false,
//	    },
//	    {
//	      id: 55, urdl: [2, 8, 8, 4], urdl_s: "2884", level: 6, row: 1,
//	      name: "", name_e: "Fujin Raijin", element: :none, rarep: false,
//	    },
//	    {
//	      id: 56, urdl: [7, 8, 3, 4], urdl_s: "7834", level: 6, row: 2,
//	      name: "", name_e: "Elvoret", element: :wind, rarep: false,
//	    },
//	    {
//	      id: 57, urdl: [4, 8, 7, 3], urdl_s: "4873", level: 6, row: 3,
//	      name: "", name_e: "X-ATM092", element: :none, rarep: false,
//	    },
//	    {
//	      id: 58, urdl: [7, 2, 8, 5], urdl_s: "7285", level: 6, row: 4,
//	      name: "", name_e: "Granaldo", element: :none, rarep: false,
//	    },
//	    {
//	      id: 59, urdl: [1, 8, 8, 3], urdl_s: "1883", level: 6, row: 5,
//	      name: "", name_e: "Gerogero", element: :poison, rarep: false,
//	    },
//	    {
//	      id: 60, urdl: [8, 2, 8, 2], urdl_s: "8282", level: 6, row: 6,
//	      name: "", name_e: "Iguion", element: :none, rarep: false,
//	    },
//	    {
//	      id: 61, urdl: [6, 8, 4, 5], urdl_s: "6845", level: 6, row: 7,
//	      name: "アバドン", name_e: "Abadon", element: :none, rarep: false,
//	    },
//	    {
//	      id: 62, urdl: [4, 8, 5, 6], urdl_s: "4856", level: 6, row: 8,
//	      name: "", name_e: "Trauma", element: :none, rarep: false,
//	    },
//	    {
//	      id: 63, urdl: [1, 8, 4, 8], urdl_s: "1848", level: 6, row: 9,
//	      name: "", name_e: "Oilboyle", element: :none, rarep: false,
//	    },
//	    {
//	      id: 64, urdl: [6, 5, 8, 4], urdl_s: "6584", level: 6, row: 10,
//	      name: "", name_e: "Shumi Tribe", element: :none, rarep: false,
//	    },
//	    {
//	      id: 65, urdl: [7, 5, 8, 1], urdl_s: "7581", level: 6, row: 11,
//	      name: "", name_e: "Krysta", element: :none, rarep: false,
//	    },
//	    {
//	      id: 66, urdl: [8, 4, 4, 8], urdl_s: "8448", level: 7, row: 1,
//	      name: "", name_e: "Propagator", element: :none, rarep: false,
//	    },
//	    {
//	      id: 67, urdl: [8, 8, 4, 4], urdl_s: "8844", level: 7, row: 2,
//	      name: "", name_e: "Jumbo Cactuar", element: :none, rarep: false,
//	    },
//	    {
//	      id: 68, urdl: [8, 5, 2, 8], urdl_s: "8528", level: 7, row: 3,
//	      name: "", name_e: "Tri-Point", element: :thunder, rarep: false,
//	    },
//	    {
//	      id: 69, urdl: [5, 6, 6, 8], urdl_s: "5668", level: 7, row: 4,
//	      name: "", name_e: "Gargantua", element: :none, rarep: false,
//	    },
//	    {
//	      id: 70, urdl: [8, 6, 7, 3], urdl_s: "8673", level: 7, row: 5,
//	      name: "", name_e: "Mobile Type 8", element: :none, rarep: false,
//	    },
//	    {
//	      id: 71, urdl: [8, 3, 5, 8], urdl_s: "8358", level: 7, row: 6,
//	      name: "", name_e: "Sphinxara", element: :none, rarep: false,
//	    },
//	    {
//	      id: 72, urdl: [8, 8, 5, 4], urdl_s: "8854", level: 7, row: 7,
//	      name: "", name_e: "Tiamat", element: :none, rarep: false,
//	    },
//	    {
//	      id: 73, urdl: [5, 7, 8, 5], urdl_s: "5785", level: 7, row: 8,
//	      name: "", name_e: "BGH251F2", element: :none, rarep: false,
//	    },
//	    {
//	      id: 74, urdl: [6, 8, 4, 7], urdl_s: "6847", level: 7, row: 9,
//	      name: "", name_e: "Red Giant", element: :none, rarep: false,
//	    },
//	    {
//	      id: 75, urdl: [1, 8, 7, 7], urdl_s: "1877", level: 7, row: 10,
//	      name: "", name_e: "Catoblepas", element: :none, rarep: false,
//	    },
//	    {
//	      id: 76, urdl: [7, 7, 8, 2], urdl_s: "7782", level: 7, row: 11,
//	      name: "", name_e: "Ultima Weapon", element: :none, rarep: false,
//	    },
//	    {
//	      id: 77, urdl: [4, 4, 8, 9], urdl_s: "4489", level: 8, row: 1,
//	      name: "", name_e: "Chubby Chocobo", element: :none, rarep: true,
//	    },
//	    {
//	      id: 78, urdl: [9, 6, 7, 3], urdl_s: "9673", level: 8, row: 2,
//	      name: "", name_e: "Angelo", element: :none, rarep: true,
//	    },
//	    {
//	      id: 79, urdl: [3, 7, 9, 6], urdl_s: "3796", level: 8, row: 3,
//	      name: "", name_e: "Gilgamesh", element: :none, rarep: true,
//	    },
//	    {
//	      id: 80, urdl: [9, 3, 9, 2], urdl_s: "9392", level: 8, row: 4,
//	      name: "", name_e: "MinMog", element: :none, rarep: true,
//	    },
//	    {
//	      id: 81, urdl: [9, 4, 8, 4], urdl_s: "9484", level: 8, row: 5,
//	      name: "", name_e: "Chicobo", element: :none, rarep: true,
//	    },
//	    {
//	      id: 82, urdl: [2, 9, 9, 4], urdl_s: "2994", level: 8, row: 6,
//	      name: "", name_e: "Quezacotl", element: :thunder, rarep: true,
//	    },
//	    {
//	      id: 83, urdl: [6, 7, 4, 9], urdl_s: "6749", level: 8, row: 7,
//	      name: "", name_e: "Shiva", element: :ice, rarep: true,
//	    },
//	    {
//	      id: 84, urdl: [9, 6, 2, 8], urdl_s: "9628", level: 8, row: 8,
//	      name: "", name_e: "Ifrit", element: :fire, rarep: true,
//	    },
//	    {
//	      id: 85, urdl: [8, 9, 6, 2], urdl_s: "8962", level: 8, row: 9,
//	      name: "", name_e: "Siren", element: :none, rarep: true,
//	    },
//	    {
//	      id: 86, urdl: [5, 1, 9, 9], urdl_s: "5199", level: 8, row: 10,
//	      name: "", name_e: "Sacred", element: :earth, rarep: true,
//	    },
//	    {
//	      id: 87, urdl: [9, 5, 2, 9], urdl_s: "9529", level: 8, row: 11,
//	      name: "", name_e: "Minotaur", element: :earth, rarep: true,
//	    },
//	    {
//	      id: 88, urdl: [8, 4, 10, 4], urdl_s: "84a4", level: 9, row: 1,
//	      name: "", name_e: "Carbuncle", element: :none, rarep: true,
//	    },
//	    {
//	      id: 89, urdl: [5, 10, 8, 3], urdl_s: "5a83", level: 9, row: 2,
//	      name: "", name_e: "Diablos", element: :none, rarep: true,
//	    },
//	    {
//	      id: 90, urdl: [7, 10, 1, 7], urdl_s: "7a17", level: 9, row: 3,
//	      name: "", name_e: "Leviathan", element: :water, rarep: true,
//	    },
//	    {
//	      id: 91, urdl: [8, 10, 3, 5], urdl_s: "8a35", level: 9, row: 4,
//	      name: "", name_e: "Odin", element: :none, rarep: true,
//	    },
//	    {
//	      id: 92, urdl: [10, 1, 7, 7], urdl_s: "a177", level: 9, row: 5,
//	      name: "", name_e: "Pandemonia", element: :wind, rarep: true,
//	    },
//	    {
//	      id: 93, urdl: [7, 4, 6, 10], urdl_s: "746a", level: 9, row: 6,
//	      name: "", name_e: "Cerberus", element: :none, rarep: true,
//	    },
//	    {
//	      id: 94, urdl: [9, 10, 4, 2], urdl_s: "9a42", level: 9, row: 7,
//	      name: "", name_e: "Alexander", element: :holy, rarep: true,
//	    },
//	    {
//	      id: 95, urdl: [7, 2, 7, 10], urdl_s: "727a", level: 9, row: 8,
//	      name: "", name_e: "Phoenix", element: :fire, rarep: true,
//	    },
//	    {
//	      id: 96, urdl: [10, 8, 2, 6], urdl_s: "a826", level: 9, row: 9,
//	      name: "", name_e: "Bahumut", element: :none, rarep: true,
//	    },
//	    {
//	      id: 97, urdl: [3, 1, 10, 10], urdl_s: "31aa", level: 9, row: 10,
//	      name: "", name_e: "Doomtrain", element: :poison, rarep: true,
//	    },
//	    {
//	      id: 98, urdl: [4, 4, 9, 10], urdl_s: "449a", level: 9, row: 11,
//	      name: "", name_e: "Eden", element: :none, rarep: true,
//	    },
//	    {
//	      id: 99, urdl: [10, 7, 2, 8], urdl_s: "a728", level: 10, row: 1,
//	      name: "", name_e: "Ward", element: :none, rarep: true,
//	    },
//	    {
//	      id: 100, urdl: [6, 7, 6, 10], urdl_s: "676a", level: 10, row: 2,
//	      name: "", name_e: "Kiros", element: :none, rarep: true,
//	    },
//	    {
//	      id: 101, urdl: [5, 10, 3, 9], urdl_s: "5a39", level: 10, row: 3,
//	      name: "", name_e: "Laguna", element: :none, rarep: true,
//	    },
//	    {
//	      id: 102, urdl: [10, 8, 6, 4], urdl_s: "a864", level: 10, row: 4,
//	      name: "", name_e: "Selphie", element: :none, rarep: true,
//	    },
//	    {
//	      id: 103, urdl: [9, 6, 10, 2], urdl_s: "96a2", level: 10, row: 5,
//	      name: "", name_e: "Quistis", element: :none, rarep: true,
//	    },
//	    {
//	      id: 104, urdl: [2, 6, 9, 10], urdl_s: "269a", level: 10, row: 6,
//	      name: "", name_e: "Irvine", element: :none, rarep: true,
//	    },
//	    {
//	      id: 105, urdl: [8, 5, 10, 6], urdl_s: "85a6", level: 10, row: 7,
//	      name: "", name_e: "Zell", element: :none, rarep: true,
//	    },
//	    {
//	      id: 106, urdl: [4, 10, 2, 10], urdl_s: "4a2a", level: 10, row: 8,
//	      name: "", name_e: "Rinoa", element: :none, rarep: true,
//	    },
//	    {
//	      id: 107, urdl: [10, 10, 3, 3], urdl_s: "aa33", level: 10, row: 9,
//	      name: "", name_e: "Edea", element: :none, rarep: true,
//	    },
//	    {
//	      id: 108, urdl: [6, 9, 10, 4], urdl_s: "69a4", level: 10, row: 10,
//	      name: "", name_e: "Seifer", element: :none, rarep: true,
//	    },
//	    {
//	      id: 109, urdl: [10, 4, 6, 9], urdl_s: "a469", level: 10, row: 11,
//	      name: "", name_e: "Squall", element: :none, rarep: true,
//	    },
//	  ]
//	  
//	  Card_Player_Table = {
//	    fc01: {
//	      name: "",
//	      name_e: "Trepe Groupie #1",
//	      rares: [Quistis_ID],
//	      rare_limit: 30,
//	      levels: [2, 5],
//	    },
//	    fc02: {
//	      name: "",
//	      name_e: "Trepe Groupie #2",
//	      rares: [Quistis_ID],
//	      rare_limit: 20,
//	      levels: [1, 3, 5],
//	    },
//	    fc03: {
//	      name: "",
//	      name_e: "Trepe Groupie #3",
//	      rares: [Quistis_ID],
//	      rare_limit: 10,
//	      levels: [1, 2, 4, 5],
//	    },
//	    zellmama: {
//	      name: "",
//	      name_e: "Ma Dincht",
//	      rares: [Zell_ID],
//	      rare_limit: 10,
//	      levels: [1, 2, 4, 5],
//	    },
//	    watts: {
//	      name: "",
//	      name_e: "Watts",
//	      rares: [Angelo_ID],
//	      rare_limit: 30,
//	      levels: [1, 4],
//	    },
//	  }
//	end
//
//	include FF8
//	
//	T = $option[:language].tap{|lang|
//	  break (String_Resources[lang] || {}).merge({
//	    card: Card_Table.map{|c|
//	      lang == :ja ? c[:name] : c[:name_e]
//	    }
//	  })
//	}
//	def t(s)
//	  f = lambda{|string_resource, keys|
//	    keys.inject(string_resource){|h, k| h.fetch(k, {})}
//	  }
//	  keys = s.split(".").map{|k|
//	    k =~ /\A\d+\Z/ ? k.to_i : k.intern
//	  }
//	  r = f.(T, keys)
//	  if r == {}
//	    r = f.(String_Resources[$option[:fallback_language]], keys)
//	  end
//	  r == {} ? s : r
//	end
//
//	def opening_situation1(rares: [], rare_limit: 10, levels:[1], state: 1, norare: false)
//	  rng = Card_RNG.new(state)
//	  deck = []
//	  
//	  if !norare
//	    rares.each{|rare_id|
//	      limit = deck.empty? ? rare_limit : rare_limit / 2
//	      deck << rare_id if rng.get % 100 < limit
//	      break if deck.size >= Deck_MAX
//	    }
//	  end
//	  
//	  while deck.size < Deck_MAX
//	    lv = levels[rng.get % levels.size]
//	    row_1 = rng.get % 11
//	    card = (lv - 1) * 11 + row_1
//	    redo if card == Pupu_ID || deck.include?(card)
//	    deck << card
//	  end
//	  initiative = (rng.get & 1) != 0
//	  
//	  return {
//	    deck: deck,                
//	    initiative: initiative,   
//	    first_state: state,        
//	    last_state: rng.state,     
//	  }
//	end
//
//	def opening_situation(state: 1, player: :zellmama, norare: false)
//	  opening_situation1(
//	    rares: Card_Player_Table[player][:rares],
//	    rare_limit: Card_Player_Table[player][:rare_limit],
//	    levels: Card_Player_Table[player][:levels],
//	    state: state,
//	    norare: norare
//	    )
//	end
//
//	def make_opening_table(from, to, state: 1, player: nil, search_type: :first, incr: 0)
//	  size = to + 1
//	  rng_state_arr = \
//	  case search_type
//	  when :first
//	    rng = Card_RNG.new(state)
//	    Array.new(size){ rng.state.tap{ rng.get } }
//	  when :counting
//	    first_state, _, count, _ = read_argv(ARGV, silent: true)
//	    rng = Card_RNG.new(first_state)
//	    max_idx = count + $option[:counting_width] 
//	    Array.new(max_idx){ rng.state.tap{ rng.get } + incr }
//	  else
//	    Array.new(size){|i| (state + i) & 0xffff_ffff }
//	  end
//	  offset_arr = \
//	  case search_type
//	  when :first
//	    Array.new(60.quo($option[:autofire_speed]).ceil) {|i|
//	      $option[:forced_incr] + i
//	    }
//	  when :counting
//	    Array.new($option[:counting_frame_width]) {|i|
//	      i - $option[:counting_frame_width] / 2
//	    }
//	  else
//	    [0]
//	  end
//	  
//	  (0..to).map{|idx|
//	    next nil if !idx.between?(from, to)
//	    
//	    offset_arr.map{|offset|
//	      rng_state = (rng_state_arr[idx] + offset) & 0xffff_ffff
//	      {
//	        index: idx,
//	        offset: offset,
//	        opening: opening_situation(state: rng_state, player: player)
//	      }
//	    }
//	  }.compact.flatten
//	end
//
//	def urdl2ids(urdl, fuzzy_ranks: false)
//	  test = \
//	  if fuzzy_ranks
//	    lambda{|v| v[:urdl].sort == urdl.sort }
//	  else
//	    lambda{|v| v[:urdl] == urdl }
//	  end
//	  
//	  Card_Table.select(&test).map{|v| v[:id] }
//	end
//
//	class OpeningPatternError < StandardError; end
//	class EmptyInput < OpeningPatternError; end
//	class UnmatchedInput < OpeningPatternError; end
//	class EmptyIDs < OpeningPatternError; end
//	class DuplicatedIDs < OpeningPatternError; end
//
//	def str2pattern1(s, fuzzy_ranks: false, silent: false)
//	  
//	  ranks_arr = s.scan(/[0-9a]{4}/i).first(5)
//	  raise UnmatchedInput, t("str2pattern.UnmatchedInput_fmt") % [s] if ranks_arr.size < 4
//	  initiative = s.scan(/[+-]/).inject(nil) {|_, c| c == "+" }
//	  
//	  custom_ranks_order = Default_Ranks_Order.chars.map{|c|
//	    $option[:ranks_order].index(c)
//	  }
//	  urdl_arr = ranks_arr.map{|ranks|
//	    ranks.chars.map{|c|
//	      c.hex.tap{|n| break 10 if n.zero? }
//	    }.values_at(*custom_ranks_order)
//	  }
//	  ids_arr = urdl_arr.map{|urdl|
//	    urdl2ids(urdl, fuzzy_ranks: fuzzy_ranks)
//	  }
//	  no_arr2s = lambda {|no_arr|
//	    no_arr.map {|no| "#%d:%s" % [no, ranks_arr[no-1]] }
//	  }
//	  begin
//	    if ids_arr.any?(&:empty?)
//	      empty_no_arr = ids_arr.map.with_index(1){|ids, no|
//	        no if ids.empty?
//	      }.compact
//	      raise EmptyIDs, t("str2pattern.EmptyIDs_fmt") % ["#{no_arr2s.(empty_no_arr).join(', ')}"]
//	    end
//	  rescue EmptyIDs => err
//	    retry_count = (retry_count || 0) + 1
//	    raise err if fuzzy_ranks || retry_count > 1
//	    
//	    if !silent
//	      puts err
//	      puts t("str2pattern.read_as_fuzzy")
//	    end
//	    ids_arr = urdl_arr.map.with_index(1){|urdl, no|
//	      fuzzyp = empty_no_arr.include?(no)
//	      urdl2ids(urdl, fuzzy_ranks: fuzzyp)
//	    }
//	    retry
//	  end
//	  
//	  ids_arr.map{|ids|
//	    if !fuzzy_ranks
//	      ids_arr.count(ids) > 1 ? ids : nil
//	    else
//	      ids.size == 1 && ids_arr.count(ids) > 1 ? ids : nil
//	    end
//	  }.tap{|arr_except_uniq|
//	    break if arr_except_uniq.compact.empty?
//	    duplicated_no_arr = arr_except_uniq.map.with_index(1){|v, no|
//	      v.nil? ? nil : no
//	    }.compact
//	    raise DuplicatedIDs, t("str2pattern.DuplicatedIDs_fmt") % ["#{no_arr2s.(duplicated_no_arr).join(", ")}"]
//	  }
//	  ids_arr.unshift([Zell_ID]) if ids_arr.size == 4
//	  
//	  {
//	    str: s,                   
//	    deck: ids_arr,            
//	    initiative: initiative,    
//	  }
//	end
//
//	def str2pattern(*args)
//	  begin
//	    str2pattern1(*args)
//	  rescue OpeningPatternError => err
//	    puts err
//	    nil
//	  end
//	end
//
//	def pattern2str(pattern)
//	  deck_s = pattern[:deck].map{|ids|
//	    names = ids.map{|id|
//	      s = t("card.#{id}")
//	      s = "*#{s}*" if $option[:highlight_cards].include?(id)
//	      s = "**#{s}**" if $option[:strong_highlight_cards].include?(id)
//	      s
//	    }.join("|")
//	    ids.size == 1 ? names : "(#{names})"
//	  }.join(", ")
//	  initiative_s = case pattern[:initiative]
//	  when true then t("initiative.player")
//	  when false then t("initiative.cpu")
//	  else t("initiative.any")
//	  end
//	  t("pattern2str_fmt") % [initiative_s, deck_s]
//	end
//
//	def opening_match?(pattern, data, fuzzy_order: false)
//	  opening = data[:opening]
//	  
//	  return if !case pattern[:initiative]
//	  when true, false
//	    pattern[:initiative] == opening[:initiative]
//	  else
//	    true
//	  end
//	  
//	  pat_deck = fuzzy_order ? pattern[:deck].sort : pattern[:deck]
//	  deck = fuzzy_order ? opening[:deck].sort : opening[:deck]
//	  pat_deck.zip(deck).all?{|ids, id|
//	    ids.include?(id)
//	  }
//	end
//
//	def opening_scanner(state: nil, player: nil, search_type: :first, incr: 0)
//	  case search_type
//	  when :first
//	    start_index = $option[:base]
//	  when :counting
//	    _, _, start_index, _ = read_argv(ARGV, silent: true)
//	  else
//	    start_index = $option[:recovery_width] / 2
//	    state = (state + incr - start_index) & 0xffff_ffff
//	  end
//
//	  order = lambda{|width|
//	    order = ([start_index] + 1.upto(width / 2).map{|offset|
//	      [start_index + offset, start_index - offset]
//	    }).flatten.select{|idx|
//	      idx >= 0
//	    }
//	    order.delete(order.max) if width.even?
//	    
//	    case $option[:order]
//	    when :reverse    then order.reverse!
//	    when :ascending  then order.sort!
//	    when :descending then order.sort!.reverse!
//	    end
//	  }.( \
//	  case search_type
//	  when :first
//	    $option[:width]
//	  when :counting
//	    $option[:counting_width]
//	  else
//	    $option[:recovery_width]
//	  end)
//	  table = make_opening_table(order.min, order.max, state: state, player: player, search_type: search_type, incr: incr)
//	  
//	  lambda{|pattern, fuzzy_order: false|
//	    order.map{|idx|
//	      data_arr = table.select{|v| v[:index] == idx }
//	      data_arr.map {|data|
//	        if opening_match?(pattern, data, fuzzy_order: fuzzy_order)
//	          {
//	            diff: idx - start_index, 
//	            index: idx,        
//	            data: data,
//	          }
//	        end
//	      }
//	    }.flatten.compact
//	  }
//	end
//
//	def next_rare?(state, rare_limit)
//	  next_rnd = ((state * 0x10dcd + 1) & 0xffff_ffff) >> 17
//	  next_rnd % 100 < rare_limit
//	end
//
//	def rare_timer1(state: 1, rare_limit: 10, width: 60, fps: 60)
//	  start = Time.now.to_f
//	  delay = $option[:delay_frame].quo(60)
//	  incr = 0
//	  incr_start = delay - $option[:forced_incr].quo(60)
//	  timer_width = 8
//	  width = [IO.console_size[1] - 1 - timer_width, width].min
//	  
//	  header = "timer".ljust(timer_width)
//	  header << "! "
//	  header << t("prompt.rare_timer")
//	  puts  header
//	  
//	  f = lambda {
//	    duration = Time.now.to_f - start
//	    incr = [((duration - incr_start) * 63).round,
//	            $option[:forced_incr] + $option[:accept_delay_frame]].max
//	    if incr <= $option[:forced_incr] + $option[:accept_delay_frame]
//	      incr = $option[:forced_incr]
//	    end
//	    rare_tbl = Array.new(width) {|i|
//	      next_rare?(state + incr + i, rare_limit)
//	    }
//	    dur_s = "%.2fs%s" % [duration - delay, rare_tbl.first ? "!" : ""]
//	    
//	    print dur_s.ljust(timer_width)
//	    print rare_tbl.map.with_index {|b, i|
//	      if b
//	        "*"
//	      elsif i.zero?
//	        "!"
//	      else
//	        "-"
//	      end
//	    }.join("")
//	    print "\r"
//	  }
//	  begin
//	    loop {
//	      f.()
//	      sleep(1.quo(fps))
//	    }
//	  ensure
//	    return incr
//	  end
//	end
//
//	def rare_timer(state: nil, player: nil)
//	  Thread.new {
//	    rare_limit = Card_Player_Table[player][:rare_limit]
//	    rare_timer1(state: state, rare_limit: rare_limit, fps: $option[:console_fps])
//	  }.tap{|t|
//	    t.kill if STDIN.gets
//	    break t.value
//	  }
//	end
//
//	def output_search_result(r)
//	  nearest_index = r.min_by{|v| [v[:diff].abs, v[:diff]] }[:index]
//	  puts "diff\tindex\toffset\tlast_state\tinitia\tdeck"
//	  
//	  r.each{|v|
//	    diff, idx, data = v.values
//	    nearestp = idx == nearest_index
//	    idx_str = (nearestp ? "*[%03d]*" : "[%03d]") % [idx]
//	    offset = data[:offset]
//	    initiative = data[:opening][:initiative]
//	    deck = "[" + data[:opening][:deck].map{|id|
//	      s = id.to_s
//	      s = "*#{s}*" if $option[:highlight_cards].include?(id)
//	      s = "**#{s}**" if $option[:strong_highlight_cards].include?(id)
//	      s
//	    }.join(", ") + "]"
//	    state = data[:opening][:last_state]
//	    puts "%+3d\t%s\t%+d\t%08x\t%s\t%s" % [diff, idx_str, offset, state, initiative, deck]
//	  }
//	end
//
//	def fuzzy2str(fuzzy)
//	  r = []
//	  r << t("fuzzy.ranks") if fuzzy.include?("r")
//	  r << t("fuzzy.order") if fuzzy.include?("o")
//	  r.empty? ? t("fuzzy.strict") : r.join(", ")
//	end
//
//	def rare_search(state: nil, player: nil, countingp: false)
//	  last_incr = rare_timer(state: state, player: player)
//	  loop {
//	    puts ""
//	    puts t("prompt.rare_search")
//	    line = Readline.readline($option[:prompt], true)
//	    case line[0]
//	    when nil then
//	      rare_search(state: state, player: player, countingp: countingp)
//	    when "h"
//	      show_pattern_help()
//	    when "q"
//	      exit
//	    else
//	      recovery_pattern = str2pattern(line, fuzzy_ranks: false)
//	      next if recovery_pattern.nil?
//	      
//	      next_search_type = countingp ? :counting : :recovery
//	      recovery_scanner = opening_scanner(state: state, player: player, search_type: next_search_type, incr: last_incr)
//	      start_search(scanner: recovery_scanner, pattern: recovery_pattern, player: player)
//	    end
//	  }
//	end
//
//	def start_search(scanner: nil, pattern: nil, player: nil)
//	  result = []
//	  $option[:fuzzy].each_with_index{|fuzzy, i|
//	    fuzzy_ranks = fuzzy.include?("r")
//	    fuzzy_order = fuzzy.include?("o")
//	    puts "-" * 60
//	    puts t("fuzzy.fmt") % [fuzzy2str(fuzzy)]
//	    
//	    pattern = str2pattern(pattern[:str], fuzzy_ranks: fuzzy_ranks, silent: true)
//	    puts pattern2str(pattern)
//
//	    r = scanner.(pattern, fuzzy_order: fuzzy_order)
//	    msg = "#{r.empty? ? "not" : r.size} found."
//	    (i == $option[:fuzzy].size - 1).tap{|lastp|
//	      msg << " retry..." if r.empty? && !lastp
//	    }
//	    puts msg
//	    if !r.empty?
//	      output_search_result(r)
//	      result = r
//	      break
//	    end
//	  }
//	  
//	  loop {
//	    puts ""
//	    puts t("prompt.after_normal_search")
//	    line = Readline.readline($option[:prompt], true)
//	    case line[0]
//	    when nil                  
//	      target = result.min_by{|v| [v[:diff].abs, v[:diff]] }
//	      if target.nil?
//	        puts t("no_target")
//	        next
//	      end
//	      
//	      last_state = target[:data][:opening][:last_state]
//	      rare_search(state: last_state, player: player)
//	    when "h"
//	      show_pattern_help()
//	    when "q"                    
//	      exit
//	    else                        
//	      new_pattern = str2pattern(line, fuzzy_ranks: false)
//	      next if new_pattern.nil?
//
//	      start_search(scanner: scanner, pattern: new_pattern, player: player)
//	    end
//	  }
//	end
//
//	def read_argv(argv, silent: false)
//	  tbl = [:unused, :elastoid, :malboro, :wedge]
//	  s = argv[0]
//	  count = 0
//	  
//	  case s
//	  when "0".."3"
//	    idx = s.to_i
//	    state_key = tbl[idx]
//	    msg = \
//	    if state_key == :unused
//	      t("read_argv.#{state_key}")
//	    else
//	      t("read_argv.pattern_fmt") % [$option[:early_quistis].capitalize, t("read_argv.#{state_key}")]
//	    end
//	    msg = t("read_argv.select_fmt") % [msg]
//	    first_state = Early_Quistis_State_Table[$option[:early_quistis]][state_key]
//	    search_type = :first
//	    msg = "%s: 0x%08x" % [msg, first_state]
//	  else
//	    first_state = s.to_i(16) & 0xffff_ffff
//	    search_type = :recovery
//	    msg = t("read_argv.direct_rng_state_fmt") % [first_state]
//	  end
//	  
//	  state = first_state
//	  if argv[1]
//	    count = argv[1].to_i
//	    state = count.tap {|n|
//	      rng = Card_RNG.new(first_state)
//	      n.times { rng.get }
//	      msg << "\n" + t("read_argv.advanced_rng_fmt") % [n , rng.state]
//	      break rng.state
//	    }
//	    search_type = :counting
//	  end
//	  puts msg if !silent
//	  [first_state, state, count, search_type]
//	end
//
//	def show_help
//	  puts t("help.first")
//	  puts ""
//	  puts t("help.last")
//	end
//
//	def show_pattern_help
//	  puts t("help.pattern")
//	end
//
//	def main
//	  if ARGV.empty?
//	    show_help()
//	    exit
//	  end
//	  
//	  first_state, state, count, search_type = read_argv(ARGV)
//	  
//	  case search_type
//	  when :first
//	    loop {
//	      puts ""
//	      puts t("prompt.second_game_method")
//	      line = Readline.readline($option[:prompt], true)
//	      case line[0]
//	      when "h"
//	        show_pattern_help()
//	      when "q"
//	        exit
//	      else
//	        pattern = str2pattern(line)
//	        next if pattern.nil?
//	        scanner = opening_scanner(state: state, player: $option[:player], search_type: :first)
//	        start_search(scanner: scanner, pattern: pattern, player: $option[:player])
//	      end
//	    }
//	  else
//	    loop {
//	      puts ""
//	      puts t("prompt.first_game_method")
//	      line = Readline.readline($option[:prompt], true)
//	      case line[0]
//	      when "h"
//	        show_pattern_help()
//	      when "q"
//	        exit
//	      else
//	        rare_search(state: state, player: $option[:player], countingp: search_type == :counting)
//	      end
//	    }
//	  end
//	end
//
//	main()
//
//}
