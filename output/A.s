extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start ConstructorDeclarationNode *****
global __DEFAULTPACKAGE_.A_CONSTRUCTOR_A#
__DEFAULTPACKAGE_.A_CONSTRUCTOR_A#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for A
extern __DEFAULTPACKAGE_.B_CONSTRUCTOR_B#
call __DEFAULTPACKAGE_.B_CONSTRUCTOR_B# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global __DEFAULTPACKAGE_.A_METHOD_foo#
__DEFAULTPACKAGE_.A_METHOD_foo#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 14
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_1:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global __DEFAULTPACKAGE_.A_METHOD_test#
__DEFAULTPACKAGE_.A_METHOD_test#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Object Initialization of type A *****
mov eax, 4
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE__DEFAULTPACKAGE_.A
mov ebx, [_VTABLE__DEFAULTPACKAGE_.A]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_3
call __exception ; nullpointerexception (for constructorcall)
__DEFAULTPACKAGE_.A_noError_3:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
; calls A
call __DEFAULTPACKAGE_.A_CONSTRUCTOR_A# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END Object Initialization *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_2:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local a
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Object Initialization of type A *****
mov eax, 4
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE__DEFAULTPACKAGE_.A
mov ebx, [_VTABLE__DEFAULTPACKAGE_.A]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_5
call __exception ; nullpointerexception (for constructorcall)
__DEFAULTPACKAGE_.A_noError_5:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
; calls A
call __DEFAULTPACKAGE_.A_CONSTRUCTOR_A# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END Object Initialization *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_4:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local b
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start Object Initialization of type A *****
mov eax, 4
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE__DEFAULTPACKAGE_.A
mov ebx, [_VTABLE__DEFAULTPACKAGE_.A]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_7
call __exception ; nullpointerexception (for constructorcall)
__DEFAULTPACKAGE_.A_noError_7:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
; calls A
call __DEFAULTPACKAGE_.A_CONSTRUCTOR_A# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END Object Initialization *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_6:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local c
; *****END LocalVariableDeclNode *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local c
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_10
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_10:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; load SI table 
mov eax, [eax] ; get vtable 
mov eax , [eax +4] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_9:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 16
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_11:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_12
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_8
__DEFAULTPACKAGE_.A_IfNEQ_12:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_8:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_13
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 96
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_14:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_13:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local c
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_17
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_17:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; load SI table 
mov eax, [eax] ; get vtable 
mov eax , [eax +0] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_16:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 14
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_18:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_19
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_15
__DEFAULTPACKAGE_.A_IfNEQ_19:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_15:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_20
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 97
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_21:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_20:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local c
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_24
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_24:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; load SI table 
mov eax, [eax] ; get vtable 
mov eax , [eax +8] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_23:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 27
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_25:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_26
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_22
__DEFAULTPACKAGE_.A_IfNEQ_26:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_22:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_27
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 98
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_28:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_27:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_31
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_31:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +36] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_30:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 16
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_32:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_33
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_29
__DEFAULTPACKAGE_.A_IfNEQ_33:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_29:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_34
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 99
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_35:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_34:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_38
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_38:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_37:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 14
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_39:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_40
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_36
__DEFAULTPACKAGE_.A_IfNEQ_40:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_36:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_41
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 100
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_42:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_41:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_45
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_45:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +40] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_44:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 27
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_46:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_47
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_43
__DEFAULTPACKAGE_.A_IfNEQ_47:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_43:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_48
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 101
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_49:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_48:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local a
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_52
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_52:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_51:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 14
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_53:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_54
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_50
__DEFAULTPACKAGE_.A_IfNEQ_54:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_50:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_55
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 102
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_56:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_55:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local a
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_59
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_59:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +36] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_58:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 16
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_60:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_61
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_57
__DEFAULTPACKAGE_.A_IfNEQ_61:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_57:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_62
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 103
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_63:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_62:
; *****END Ifstmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local a
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne __DEFAULTPACKAGE_.A_noError_66
call __exception ; nullpointerexception (for methodcall)
__DEFAULTPACKAGE_.A_noError_66:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +40] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_65:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 27
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_67:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jne __DEFAULTPACKAGE_.A_IfNEQ_68
mov eax, 0
jmp __DEFAULTPACKAGE_.A_BinOpEndLabel_64
__DEFAULTPACKAGE_.A_IfNEQ_68:
mov eax, 1
__DEFAULTPACKAGE_.A_BinOpEndLabel_64:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je __DEFAULTPACKAGE_.A_IfThenStmt_69
push esi; 
mov esi, esp ; block start
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 104
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_70:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
mov esp, esi ; block end
pop esi
__DEFAULTPACKAGE_.A_IfThenStmt_69:
; *****END Ifstmt node *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 123
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
__DEFAULTPACKAGE_.A_BinOpEndLabel_71:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
